package com.prova.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prova.domains.Venda;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VendaDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenda = LocalDate.now();

    @Positive(message = "O valor total da venda não pode ser R$0,00 ou negativo!")
    private double valorTotal;
    private Long cliente;
    List<Integer> produtos = new ArrayList<>();

    public VendaDTO(){
    }

    public VendaDTO(Venda venda) {
        this.id = venda.getId();
        this.dataVenda = venda.getDataVenda();
        this.valorTotal = venda.getValorTotal();
        this.cliente = venda.getCliente().getId();
        this.produtos = venda.getProdutos();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public List<Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Integer> produtos) {
        this.produtos = produtos;
    }

}
