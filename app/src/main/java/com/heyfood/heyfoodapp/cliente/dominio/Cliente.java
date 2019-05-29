package com.heyfood.heyfoodapp.cliente.dominio;

import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

/**
 * Created by GABRIEL.CABOCLO on 29/05/2019.
 */

public class Cliente {
    private int id;
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
