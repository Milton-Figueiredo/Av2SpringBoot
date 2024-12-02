package com.prova.domains;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nomeCarro;
    private String marca;
    private double valorCompra;
    private double valorVenda;
    private String kilometragemCarro;

    @ManyToOne
    @JoinColumn(name = "idvenda")
    private Venda venda;
    private String tipoProduto;

    public Carro() {
    }

    public Carro(int id, String nomeCarro, String marca, double valorCompra, double valorVenda, String kilometragemCarro, String tipoProduto, Venda venda) {
        this.id = id;
        this.nomeCarro = nomeCarro;
        this.marca = marca;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.kilometragemCarro = kilometragemCarro;
        this.venda = venda;
        this.tipoProduto = tipoProduto;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return id == carro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
