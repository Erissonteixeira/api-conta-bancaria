package io.github.Erissonteixeira.api_conta_bancaria.service;

import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaRequestDto;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaResponseDto;

import java.util.List;

public interface ContaService{
    ContaResponseDto criarConta(ContaRequestDto dto);
    ContaResponseDto buscarPorId(Long id);
    ContaResponseDto atualizar(Long id, ContaRequestDto dto);
    List<ContaResponseDto> listar();
    void deletar(Long id);


}

