package com.heyfood.heyfoodapp.usuario.dominio;

/**
 * Created by bruno.silvaleite on 10/05/2019.
 */

public class Usuario {
    private String id;
    private String login;
    private String senha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
