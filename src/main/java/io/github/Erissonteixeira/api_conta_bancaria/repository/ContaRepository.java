package io.github.Erissonteixeira.api_conta_bancaria.repository;

import io.github.Erissonteixeira.api_conta_bancaria.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
}
