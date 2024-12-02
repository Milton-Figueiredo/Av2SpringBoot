package com.prova.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prova.domains.dtos.ClienteDTO;
import com.prova.domains.enums.TipoPessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    List<Venda> vendas = new ArrayList<>();

    public Cliente() {
        super();
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public Cliente(String cpf, String nomePessoa, Long id) {
        super(cpf, nomePessoa, id);
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public Cliente(ClienteDTO obj) {
        this.id = obj.getId();
        this.nomePessoa = obj.getNomePessoa();
        this.cpf = obj.getCpf();
        this.dataCadastro = obj.getDataCadastro();
        //this.tipoPessoa = obj.getTipoPessoa().stream().map(x -> x.getId()).collect(Collectors.toSet());
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

}
