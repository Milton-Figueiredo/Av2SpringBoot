package com.prova.services;

import com.prova.domains.Funcionario;
import com.prova.domains.dtos.FuncionarioDTO;
import com.prova.repositories.FuncionarioRepository;
import com.prova.services.exceptions.DataIntegrityViolationException;
import com.prova.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<FuncionarioDTO> findAll() {
        return funcionarioRepository.findAll().stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> obj = funcionarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Funcionário não encontrado no sistema! ID: " + id));
    }

    public Funcionario create(FuncionarioDTO objDto) {
        objDto.setId(null);
        Funcionario newObj = new Funcionario(objDto);
        return funcionarioRepository.save(newObj);
    }

    public Funcionario update(Long id, FuncionarioDTO objDto) {
        objDto.setId(id);
        Funcionario oldObj = findById(id);
        oldObj = new Funcionario(objDto);
        return funcionarioRepository.save(oldObj);
    }

    public void delete(Long id) {
        Funcionario obj = findById(id);
        funcionarioRepository.deleteById(id);
    }

}
