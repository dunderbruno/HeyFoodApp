package com.heyfood.heyfoodapp.proprietario.dominio;

import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

public class Proprietario {
    private int id;
    private Restaurante restaurante;
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
