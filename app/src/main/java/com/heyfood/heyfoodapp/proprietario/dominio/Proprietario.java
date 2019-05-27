package com.heyfood.heyfoodapp.proprietario.dominio;

import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

public class Proprietario {
    private Restaurante restaurante;
    private Usuario usuario;

    public Proprietario(Restaurante restaurante, Usuario usuario) {
        this.restaurante = restaurante;
        this.usuario = usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
