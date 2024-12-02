package com.prova.domains.dtos;

import com.prova.domains.Carro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class CarroDTO {

    private int id;

    @NotNull(message = "O nome do carro não pode ser nulo!")
    @NotBlank(message = "O nome do carro não pode ser em branco!")
    private String nomeCarro;

    @NotNull(message = "A marca do carro não pode ser nula!")
    @NotBlank(message = "A marca do carro não pode ser em branco!")
    private String marca;

    @Positive(message = "O valor de compra não pode ser R$0,00 ou negativo!")
    private double valorCompra;

    @Positive(message = "O valor de venda não pode ser R$0,00 ou negativo!")
    private double valorVenda;

    @NotNull(message = "A quilometragem do carro não pode ser nula!")
    @NotBlank(message = "A quilometragem do carro não pode ser em branco!")
    private String kilometragemCarro;
    private UUID venda;

    @NotNull(message = "O tipo do carro não pode ser nulo!")
    @NotBlank(message = "O tipo do carro não pode ser em branco!")
    private String tipoProduto;

    public CarroDTO() {
    }

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.nomeCarro = carro.getNomeCarro();
        this.marca = carro.getMarca();
        this.valorCompra = carro.getValorCompra();
        this.valorVenda = carro.getValorVenda();
        this.kilometragemCarro = carro.getKilometragemCarro();
        this.venda = carro.getVenda() != null ? carro.getVenda().getId() : null;
        this.tipoProduto = carro.getTipoProduto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getKilometragemCarro() {
        return kilometragemCarro;
    }

    public void setKilometragemCarro(String kilometragemCarro) {
        this.kilometragemCarro = kilometragemCarro;
    }

    public UUID getVenda() {
        return venda;
    }

    public void setVenda(UUID venda) {
        this.venda = venda;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
