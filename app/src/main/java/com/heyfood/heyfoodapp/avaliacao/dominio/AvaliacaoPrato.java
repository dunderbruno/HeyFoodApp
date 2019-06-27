package com.heyfood.heyfoodapp.avaliacao.dominio;

import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.prato.dominio.Prato;


public class AvaliacaoPrato {
    private long id;
    private Prato prato;
    private Cliente cliente;
    private int nota;

    public long getId() {
        return id;
    }

    public Prato getPrato() {
        return prato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNota() {
        return nota;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
