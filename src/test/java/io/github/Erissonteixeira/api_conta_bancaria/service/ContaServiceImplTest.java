package io.github.Erissonteixeira.api_conta_bancaria.service;

import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaRequestDto;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaResponseDto;
import io.github.Erissonteixeira.api_conta_bancaria.model.Conta;
import io.github.Erissonteixeira.api_conta_bancaria.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ContaServiceImplTest{
    @Mock
    private ContaRepository contaRepository;
    @InjectMocks
    private ContaServiceImpl contaService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void deveCriarContaComSucesso(){
        ContaRequestDto dto = new ContaRequestDto("Erisson", 1000.0);

        Conta contaMock = new Conta("Erisson", 1000.0);
        contaMock.setSaldo(1000.0);

        when(contaRepository.save(any(Conta.class))).thenReturn(contaMock);

        ContaResponseDto resposta = contaService.criarConta(dto);

        assertEquals("Erisson", resposta.titular());
        assertEquals(1000.0,resposta.saldo());
    }
    @Test
    void deveBuscarContaPorIdComSucesso(){
        Long id = 1l;
        Conta contaMock = new Conta("Erisson", 1000.0);
        contaMock.setSaldo(1000.0);

        java.lang.reflect.Field field = null;
        try{
            field = Conta.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(contaMock, id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        when(contaRepository.findById(id)).thenReturn(java.util.Optional.of(contaMock));

        ContaResponseDto resposta = contaService.buscarPorId(id);

        assertEquals(id, resposta.id());
        assertEquals("Erisson", resposta.titular());
        assertEquals(1000.0, resposta.saldo());
    }
    @Test
    void deveLancarExcecaoQuandoContaNaoExistir(){
        Long id = 1l;

        when(contaRepository.findById(id)).thenReturn(java.util.Optional.empty());

        try {
            contaService.buscarPorId(id);
        } catch (RuntimeException e){
            assertEquals("Conta não encontrada", e.getMessage());
            return;
        }
        throw new AssertionError("Era esperada uma RuntimeException, mas nada foi lançado");
    }
    @Test
    void deveAtualizarContaComSucesso(){
        Long id = 1l;
        ContaRequestDto dto = new ContaRequestDto("Erisson Atualizado", 2000.0);

        Conta contaMock = new Conta("Erisson", 1000.0);
        try {
            java.lang.reflect.Field field = Conta.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(contaMock, id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        when(contaRepository.findById(id)).thenReturn(java.util.Optional.of(contaMock));

        when(contaRepository.save(contaMock)).thenReturn(contaMock);

        ContaResponseDto resposta = contaService.atualizar(id, dto);

        assertEquals(id, resposta.id());
        assertEquals("Erisson Atualizado", resposta.titular());
        assertEquals(2000.0, resposta.saldo());
    }
    @Test
    void deveLancarExcecaoQuandoAtualizarContaInexistente(){
        Long id = 1l;
        ContaRequestDto dto = new ContaRequestDto("Erisson", 1000.0);

        when(contaRepository.findById(id)).thenReturn(java.util.Optional.empty());
        try {
            contaService.atualizar(id, dto);
        } catch (RuntimeException e) {
            assertEquals("Conta não encontrada", e.getMessage());

            return;
        }
        throw new AssertionError("Era esperada uma RuntimeException, mas nada foi lançado");
    }
    @Test
    void deveListarTodasAsContasComSucesso(){
        Conta conta1 = new Conta("Erisson", 1000.0);
        Conta conta2 = new Conta("Teixeira", 2000.0);

        try {
            var field = Conta.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(conta1, 1l);
            field.set(conta2, 2l);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        List<Conta> contasMock = List.of(conta1, conta2);

        when(contaRepository.findAll()).thenReturn(contasMock);

        List<ContaResponseDto> resposta = contaService.listar();

        assertEquals(2, resposta.size());
        assertEquals("Erisson", resposta.get(0).titular());
        assertEquals("Teixeira", resposta.get(1).titular());
    }
    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremContas(){
        when(contaRepository.findAll()).thenReturn(List.of());

        List<ContaResponseDto> resposta = contaService.listar();

        assertEquals(0, resposta.size());
    }
    @Test
    void deveDeletarContaComSucesso(){
        Long id = 1l;

        Conta contaMock = new Conta("Erisson", 1000.0);

        try {
            var field = Conta.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(contaMock, id);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        when(contaRepository.findById(id)).thenReturn(java.util.Optional.of(contaMock));

        contaService.deletar(id);

        org.mockito.Mockito.verify(contaRepository).delete(contaMock);
    }
}

