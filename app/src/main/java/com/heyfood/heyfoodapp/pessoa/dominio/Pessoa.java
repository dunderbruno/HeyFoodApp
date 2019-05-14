package com.heyfood.heyfoodapp.pessoa.dominio;

import java.util.Date;

/**
 * Created by bruno.silvaleite on 10/05/2019.
 */

public class Pessoa {
    private String nome;
    private String cpf;
    private Date dataNAscimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNAscimento() {
        return dataNAscimento;
    }

    public void setDataNAscimento(Date dataNAscimento) {
        this.dataNAscimento = dataNAscimento;
    }
}
