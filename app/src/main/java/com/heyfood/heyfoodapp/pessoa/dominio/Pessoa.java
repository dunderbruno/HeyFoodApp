package com.heyfood.heyfoodapp.pessoa.dominio;

import java.util.Date;

/**
 * Created by bruno.silvaleite on 10/05/2019.
 */

public class Pessoa {
    private String id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private TipoGenero genero;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNAscimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoGenero getGenero(){ return genero; }

    public void setGenero(TipoGenero genero){ this.genero = genero; }

}
