package com.prova.services;

import com.prova.domains.Cliente;
import com.prova.domains.dtos.ClienteDTO;
import com.prova.repositories.ClienteRepository;
import com.prova.services.exceptions.DataIntegrityViolationException;
import com.prova.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado no sistema! ID: " + id));
    }

    public Cliente create(ClienteDTO objDto) {
        objDto.setId(null);
        Cliente newObj = new Cliente(objDto);
        return clienteRepository.save(newObj);
    }

    public Cliente update(Long id, ClienteDTO objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        oldObj = new Cliente(objDto);
        return clienteRepository.save(oldObj);
    }

    public void delete(Long id) {
        Cliente obj = findById(id);
        clienteRepository.deleteById(id);
    }


}
