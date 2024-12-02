package com.prova.resources;

import com.prova.domains.Carro;
import com.prova.domains.dtos.CarroDTO;
import com.prova.services.CarroService;
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
@RequestMapping(value = "/carro")
@Tag(name = "Carros", description = "API para gerenciamento de carros")
public class CarroResource {

    @Autowired
    private CarroService carroService;

    @Operation(summary = "Listar todos os carros", description = "Retorna uma lista com todos os carros cadastrados.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CarroDTO>> findAll() {
        return ResponseEntity.ok().body(carroService.findAll());
    }

    @Operation(summary = "Buscar carro por ID", description = "Retorna um carro com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable int id) {
        Carro obj = this.carroService.findById(id);
        return ResponseEntity.ok().body(new CarroDTO(obj));
    }

    @Operation(summary = "Criar um novo carro", description = "Cria um novo carro com base nos dados fornecidos.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CarroDTO> create(@Valid @RequestBody CarroDTO objDto) {
        Carro newObj = carroService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Atualizar carro", description = "Atualiza os dados do carro.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> update(@PathVariable int id, @Valid @RequestBody CarroDTO objDto) {
        Carro obj = carroService.update(id, objDto);
        return ResponseEntity.ok().body(new CarroDTO(obj));
    }

    @Operation(summary = "Deletar carro", description = "Deleta um carro com base no ID fornecido.")
    @PreAuthorize("hasRole('FUNCIONARIO') or hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> delete(@PathVariable int id) {
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
