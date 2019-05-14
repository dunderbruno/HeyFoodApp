package com.heyfood.heyfoodapp.pessoa.dominio;

/**
 * Created by gabriel.caboclo on 14/05/2019.
 */

public enum TipoGenero {
    MASCULINO("Masculino"), FEMININO("Feminino"), OUTROS("Outros");
    private final String tipoGenero;

    TipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public String getTipoGenero(){
        return tipoGenero;
    }

}
