package com.prova.domains;

import com.prova.domains.dtos.FuncionarioDTO;
import com.prova.domains.enums.TipoPessoa;
import jakarta.persistence.Entity;
import java.util.stream.Collectors;

@Entity
public class Funcionario extends Pessoa {

    public Funcionario() {
        super();
        addTipoPessoa(TipoPessoa.FUNCIONARIO);
    }

    public Funcionario(String cpf, String nomePessoa, Long id) {
        super(cpf, nomePessoa, id);
        addTipoPessoa(TipoPessoa.FUNCIONARIO);
    }

    public Funcionario(FuncionarioDTO obj) {
        this.id = obj.getId();
        this.nomePessoa = obj.getNomePessoa();
        this.cpf = obj.getCpf();
        this.dataCadastro = obj.getDataCadastro();
        this.tipoPessoa = obj.getTipoPessoa().stream().map(x -> x.getId()).collect(Collectors.toSet());
        addTipoPessoa(TipoPessoa.CLIENTE);
        addTipoPessoa(TipoPessoa.FUNCIONARIO);
    }
    
}
