package com.prova.resources;

import com.prova.domains.Cliente;
import com.prova.domains.dtos.ClienteDTO;
import com.prova.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente", description = "API para gerenciamento de clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN') or hasRole('CLIENTE')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        Cliente obj = this.clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    

    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente com base nos dados fornecidos.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDto) {
        Cliente newObj = clienteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados do cliente.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    private ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO objDto) {
        Cliente obj = clienteService.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @Operation(summary = "Deletar cliente", description = "Deleta um cliente com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
