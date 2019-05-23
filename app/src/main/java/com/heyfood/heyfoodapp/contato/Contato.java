package com.heyfood.heyfoodapp.contato;

public class Contato {
    private String telefone;
    private String email;
    private String site;

    public Contato(String telefone, String email, String site) {
        this.telefone = telefone;
        this.email = email;
        this.site = site;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSite() {
        return site;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
