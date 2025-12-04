package io.github.Erissonteixeira.api_conta_bancaria.service;

import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaRequestDto;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaResponseDto;
import io.github.Erissonteixeira.api_conta_bancaria.model.Conta;
import io.github.Erissonteixeira.api_conta_bancaria.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService{
    private final ContaRepository contaRepository;


    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }
    @Override
    public ContaResponseDto criarConta(ContaRequestDto dto){
        Conta conta = new Conta(
                dto.titular(),
                dto.saldoInicial()
        );
        Conta contaSalva = contaRepository.save(conta);
        return new ContaResponseDto(
                contaSalva.getId(),
                contaSalva.getTitular(),
                contaSalva.getSaldo()
        );
    }
    @Override
    public ContaResponseDto buscarPorId(Long id){
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return new ContaResponseDto(
                conta.getId(),
                conta.getTitular(),
                conta.getSaldo()
        );
    }
    @Override
    public ContaResponseDto atualizar(Long id, ContaRequestDto dto){
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setTitular(dto.titular());
        conta.setSaldo(dto.saldoInicial());

        Conta contaAtulizada = contaRepository.save(conta);
        return new ContaResponseDto(
                contaAtulizada.getId(),
                contaAtulizada.getTitular(),
                contaAtulizada.getSaldo()
        );

    }
    @Override
    public List<ContaResponseDto> listar(){
        return contaRepository.findAll()
                .stream()
                .map(conta -> new ContaResponseDto(
                        conta.getId(),
                        conta.getTitular(),
                        conta.getSaldo()
                ))
                .toList();
    }
}
