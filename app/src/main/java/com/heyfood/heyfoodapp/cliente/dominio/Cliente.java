package com.heyfood.heyfoodapp.cliente.dominio;

import java.util.Date;

/**
 * Created by bruno.silvaleite on 10/05/2019.
 */

public class Cliente {
    private String id;
    private Date dataCadastro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
