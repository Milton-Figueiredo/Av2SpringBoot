package com.prova.resources;

import com.prova.domains.Venda;
import com.prova.domains.dtos.VendaDTO;
import com.prova.services.VendaService;
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
import java.util.UUID;

@RestController
@RequestMapping(value = "/venda")
@Tag(name = "Vendas", description = "API para gerenciamento de vendas")
public class VendaResource {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "Listar todas as vendas", description = "Retorna uma lista com todas as vendas cadastradas.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll() {
        return ResponseEntity.ok().body(vendaService.findAll());
    }

    @Operation(summary = "Buscar venda por ID", description = "Retorna uma venda com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable UUID id) {
        Venda obj = this.vendaService.findById(id);
        return ResponseEntity.ok().body(new VendaDTO(obj));
    }

    @Operation(summary = "Criar uma nova venda", description = "Cria uma nova venda com base nos dados fornecidos.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<VendaDTO> create(@Valid @RequestBody VendaDTO objDto) {
        Venda newObj = vendaService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar venda", description = "Atualiza os dados da venda.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> update(@PathVariable UUID id, @Valid @RequestBody VendaDTO objDto) {
        Venda obj = vendaService.update(id, objDto);
        return ResponseEntity.ok().body(new VendaDTO(obj));
    }

    @Operation(summary = "Deletar venda", description = "Deleta uma venda com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> delete(@PathVariable UUID id) {
        vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
