package io.github.Erissonteixeira.api_conta_bancaria.controller;

import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaRequestDto;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaResponseDto;
import io.github.Erissonteixeira.api_conta_bancaria.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contas")
@Tag(name = "Contas", description = "Operações para gerenciamento de contas bancárias")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @Operation(
            summary = "Criar nova conta",
            description = "Cria uma nova conta bancária no sistema"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Conta criada com sucesso",
            content = @Content(schema = @Schema(implementation = ContaResponseDto.class))
    )
    @PostMapping
    public ContaResponseDto criar(@RequestBody ContaRequestDto dto) {
        return contaService.criarConta(dto);
    }

    @Operation(
            summary = "Buscar conta por ID",
            description = "Retorna os dados de uma conta existente"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Conta encontrada",
            content = @Content(schema = @Schema(implementation = ContaResponseDto.class))
    )
    @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    @GetMapping("/{id}")
    public ContaResponseDto buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }

    @Operation(
            summary = "Atualizar uma conta",
            description = "Atualiza os dados da conta informada"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Conta atualizada com sucesso",
            content = @Content(schema = @Schema(implementation = ContaResponseDto.class))
    )
    @PutMapping("/{id}")
    public ContaResponseDto atualizar(@PathVariable Long id, @RequestBody ContaRequestDto dto){
        return contaService.atualizar(id, dto);
    }

    @Operation(
            summary = "Deletar conta",
            description = "Remove uma conta existente do sistema"
    )
    @ApiResponse(responseCode = "204", description = "Conta deletada")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        contaService.deletar(id);
    }
}
