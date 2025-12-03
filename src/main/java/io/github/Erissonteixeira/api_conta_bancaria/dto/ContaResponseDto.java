package io.github.Erissonteixeira.api_conta_bancaria.dto;

public record ContaResponseDto(
        Long id,
        String titular,
        double saldo
){
}
