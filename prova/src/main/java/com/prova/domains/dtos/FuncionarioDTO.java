package com.prova.domains.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prova.domains.Funcionario;
import com.prova.domains.enums.TipoPessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FuncionarioDTO {

    protected Long id;

    @NotNull(message = "O nome não pode ser nulo!")
    @NotBlank(message = "O nome não pode ser em branco!")
    protected String nomePessoa;

    @NotNull(message = "O CPF não pode ser nulo!")
    @NotBlank(message = "O CPF não pode ser em branco!")
    protected String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCadastro = LocalDate.now();
    protected Set<Integer> tipoPessoa = new HashSet<>();

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nomePessoa = funcionario.getNomePessoa();
        this.cpf = funcionario.getCpf();
        this.dataCadastro = funcionario.getDataCadastro();
        this.tipoPessoa = funcionario.getTipoPessoa().stream().map(x -> x.getId()).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Set<TipoPessoa> getTipoPessoa() {
        return tipoPessoa.stream().map(x -> TipoPessoa.toEnum(x)).collect(Collectors.toSet());
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa.add(tipoPessoa.getId());
    }
}
