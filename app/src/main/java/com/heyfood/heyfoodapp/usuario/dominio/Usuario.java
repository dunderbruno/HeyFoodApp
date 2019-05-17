package com.heyfood.heyfoodapp.usuario.dominio;

import com.heyfood.heyfoodapp.pessoa.dominio.Pessoa;

/**
 * Created by bruno.silvaleite on 10/05/2019.
 */

public class Usuario {
    private int id;
    private Pessoa pessoa;
    private String login;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() { return pessoa; }

    public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
