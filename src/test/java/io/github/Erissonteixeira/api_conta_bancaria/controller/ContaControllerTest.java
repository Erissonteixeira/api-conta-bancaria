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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mockMvc = MockMvcBuilders.standaloneSetup(contaController).build(); // <- corrigido
    }

    @Test
    void deveCriarContaComSucesso() throws Exception {
        ContaRequestDto dto = new ContaRequestDto("Erisson", 1000.0);
        ContaResponseDto respostaMock = new ContaResponseDto(1L, "Erisson", 1000.0);

        when(contaService.criarConta(any(ContaRequestDto.class))).thenReturn(respostaMock);

        mockMvc.perform(post("/api/v1/contas")
                        .contentType(MediaType.APPLICATION_JSON) // <- corrigido
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titular").value("Erisson"))
                .andExpect(jsonPath("$.saldo").value(1000.0));
    }
}
