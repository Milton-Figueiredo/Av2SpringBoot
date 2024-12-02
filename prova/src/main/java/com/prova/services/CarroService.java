package com.prova.services;

import com.prova.domains.Carro;
import com.prova.domains.Venda;
import com.prova.domains.dtos.CarroDTO;
import com.prova.repositories.CarroRepository;
import com.prova.services.exceptions.DataIntegrityViolationException;
import com.prova.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private VendaService vendaService;

    public List<CarroDTO> findAll() {
        return carroRepository.findAll().stream().map(obj -> new CarroDTO(obj)).collect(Collectors.toList());
    }

    public Carro findById(int id) {
        Optional<Carro> obj = carroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado no sistema! ID: " + id));
    }

    private Carro newCarro(CarroDTO objDto) {
        Venda venda = objDto.getVenda() != null ? vendaService.findById(objDto.getVenda()) : null; // Garantir que venda seja nulo caso não esteja presente no DTO.

        Carro carro = new Carro();
        
        if (objDto.getId() != 0) {
            carro.setId(objDto.getId());
        }

        carro.setVenda(venda); // Definindo a venda no carro
        carro.setNomeCarro(objDto.getNomeCarro());
        carro.setMarca(objDto.getMarca());
        carro.setValorCompra(objDto.getValorCompra());
        carro.setValorVenda(objDto.getValorVenda());
        carro.setKilometragemCarro(objDto.getKilometragemCarro());
        carro.setTipoProduto(objDto.getTipoProduto());

        return carro;
    }

    public Carro create(CarroDTO carroDto) {
        return carroRepository.save(newCarro(carroDto));
    }

    public Carro update(int id, CarroDTO objDto) {
        objDto.setId(id);
        Carro oldObj = findById(id);
        oldObj = newCarro(objDto);
        return carroRepository.save(oldObj);
    }

    public void delete(@PathVariable int id) {
        Carro obj = findById(id);
        if (obj.getVenda() != null) {
            throw new DataIntegrityViolationException("Carro já faz parte de vendas! Impossível deletar.");
        }
        carroRepository.deleteById(id);
    }
}
