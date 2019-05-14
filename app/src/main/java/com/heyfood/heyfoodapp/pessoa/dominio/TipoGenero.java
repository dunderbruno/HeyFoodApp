package com.heyfood.heyfoodapp.pessoa.dominio;

/**
 * Created by gabriel.caboclo on 14/05/2019.
 */

public enum TipoGenero {
    MASCULINO("Masculino"), FEMININO("Feminino"), OUTROS("Outros");
    private final String TIPO_GENERO;

    TipoGenero(String tipoGenero) {
        this.TIPO_GENERO = tipoGenero;
    }

    public String getTipoGenero(){
        return TIPO_GENERO;
    }

}
