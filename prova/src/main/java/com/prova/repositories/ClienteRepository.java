package com.prova.repositories;

import com.prova.domains.Cliente;
import com.prova.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Pessoa> findByNomePessoa(String nomePessoa);
}
