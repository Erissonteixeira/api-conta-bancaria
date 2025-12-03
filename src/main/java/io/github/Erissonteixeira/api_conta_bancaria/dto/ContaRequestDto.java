package io.github.Erissonteixeira.api_conta_bancaria.dto;

public record ContaRequestDto(
        String titular,
        double saldoInicial
){
}
