package com.heyfood.heyfoodapp.cliente.dominio;

import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

import java.util.Date;

/**
 * Created by bruno.silvaleite on 10/05/2019.
 */

public class Cliente {
    private String id;
    private Usuario usuario;
    private Date dataCadastro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {return usuario; }

    public void setUsuario(Usuario usuario) {this.usuario = usuario; }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
