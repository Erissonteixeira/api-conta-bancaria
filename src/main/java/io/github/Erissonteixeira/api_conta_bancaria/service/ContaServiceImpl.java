package io.github.Erissonteixeira.api_conta_bancaria.service;

import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaRequestDto;
import io.github.Erissonteixeira.api_conta_bancaria.dto.ContaResponseDto;
import io.github.Erissonteixeira.api_conta_bancaria.model.Conta;
import io.github.Erissonteixeira.api_conta_bancaria.repository.ContaRepository;
import org.springframework.stereotype.Service;

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
}
