package com.prova.resources;

import com.prova.domains.Funcionario;
import com.prova.domains.dtos.FuncionarioDTO;
import com.prova.services.FuncionarioService;
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
@RequestMapping(value = "/funcionario")
@Tag(name = "Funcionário", description = "API para gerenciamento de funcionários")
public class FuncionarioResource {

    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(summary = "Listar todos os funcionários", description = "Retorna uma lista com todos os funcionários cadastrados.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        return ResponseEntity.ok().body(funcionarioService.findAll());
    }

    @Operation(summary = "Buscar funcionário por ID", description = "Retorna um funcionário com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        Funcionario obj = this.funcionarioService.findById(id);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    

    @Operation(summary = "Criar um novo funcionário", description = "Cria um novo funcionário com base nos dados fornecidos.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO objDto) {
        Funcionario newObj = funcionarioService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar funcionário", description = "Atualiza os dados do funcionário.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO objDto) {
        Funcionario obj = funcionarioService.update(id, objDto);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    @Operation(summary = "Deletar funcionário", description = "Deleta um funcionário com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
