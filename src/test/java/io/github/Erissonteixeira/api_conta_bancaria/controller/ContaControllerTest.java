package io.github.Erissonteixeira.api_conta_bancaria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaRequestDto;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaResponseDto;
import io.github.Erissonteixeira.api_conta_bancaria.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private ContaController contaController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contaController).build();
    }

    @Test
    void deveCriarContaComSucesso() throws Exception {
        ContaRequestDto dto = new ContaRequestDto("Erisson", 1000.0);
        ContaResponseDto respostaMock = new ContaResponseDto(1L, "Erisson", 1000.0);

        when(contaService.criarConta(any(ContaRequestDto.class))).thenReturn(respostaMock);

        mockMvc.perform(post("/api/v1/contas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titular").value("Erisson"))
                .andExpect(jsonPath("$.saldo").value(1000.0));
    }
    @Test
    void deveBuscarContaPorIdComSucesso() throws Exception{
        Long id = 1l;
        ContaResponseDto respostaMock = new ContaResponseDto(id, "Erisson", 1000.0);

        when(contaService.buscarPorId(id)).thenReturn(respostaMock);

        mockMvc.perform(get("/api/v1/contas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.titular").value("Erisson"))
                .andExpect(jsonPath("$.saldo").value(1000.0));
    }
    @Test
    void deveAtualizarContaComSucesso() throws Exception{
        Long id = 1l;
        ContaRequestDto dto = new ContaRequestDto("Erisson Atualizado", 2000.0);
        ContaResponseDto respostaMock = new ContaResponseDto(id, "Erisson Atualizado", 2000.0);

        when(contaService.atualizar(any(Long.class), any(ContaRequestDto.class))).thenReturn(respostaMock);

        mockMvc.perform(put("/api/v1/contas/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.titular").value("Erisson Atualizado"))
                .andExpect(jsonPath("$.saldo").value(2000.0));
    }
}
