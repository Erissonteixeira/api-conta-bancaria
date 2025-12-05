package io.github.Erissonteixeira.api_conta_bancaria.controller;

import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaRequestDto;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaResponseDto;
import io.github.Erissonteixeira.api_conta_bancaria.service.ContaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ContaResponseDto criar(@RequestBody ContaRequestDto dto){
        return contaService.criarConta(dto);
    }
    @GetMapping("/{id}")
    public ContaResponseDto buscarPorId(@PathVariable Long id){
        return contaService.buscarPorId(id);
    }
