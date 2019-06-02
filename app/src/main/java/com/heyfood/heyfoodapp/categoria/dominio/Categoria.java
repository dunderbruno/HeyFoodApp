package com.heyfood.heyfoodapp.categoria.dominio;

public class Categoria {
    private int id;
    private Boolean brasileira;
    private Boolean mexicana;
    private Boolean japonesa;
    private Boolean italiana;
    private Boolean francesa;

    public int getId() {
        return id;
    }

    public Boolean getBrasileira() {
        return brasileira;
    }

    public Boolean getMexicana() {
        return mexicana;
    }

    public Boolean getJaponesa() {
        return japonesa;
    }

    public Boolean getItaliana() {
        return italiana;
    }

    public Boolean getFrancesa() {
        return francesa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrasileira(Boolean brasileira) {
        this.brasileira = brasileira;
    }

    public void setMexicana(Boolean mexicana) {
        this.mexicana = mexicana;
    }

    public void setJaponesa(Boolean japonesa) {
        this.japonesa = japonesa;
    }

    public void setItaliana(Boolean italiana) {
        this.italiana = italiana;
    }

    public void setFrancesa(Boolean francesa) {
        this.francesa = francesa;
    }
}
