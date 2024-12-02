package com.prova.repositories;

import com.prova.domains.Funcionario;
import com.prova.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Pessoa> findByNomePessoa(String nomePessoa);
}
