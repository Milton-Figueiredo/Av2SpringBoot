package com.prova.repositories;

import com.prova.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    
    Optional<Pessoa> findByNomePessoa(String nomePessoa);
}
