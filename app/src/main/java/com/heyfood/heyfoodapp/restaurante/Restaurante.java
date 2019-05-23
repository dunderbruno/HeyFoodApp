package com.heyfood.heyfoodapp.restaurante;

import java.util.Arrays;

public class Restaurante {
    private String nome;
    private float notaMedia;
    private String cnpj;
    private String[] comentarios;

    public Restaurante(String nome, float notaMedia, String cnpj, String[] comentarios) {
        this.nome = nome;
        this.notaMedia = notaMedia;
        this.cnpj = cnpj;
        this.comentarios = comentarios;
    }

    public String getNome() {
        return nome;
    }

    public float getNotaMedia() {
        return notaMedia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String[] getComentarios() {
        return comentarios;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setComentarios(String[] comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "nome='" + nome + '\'' +
                ", notaMedia=" + notaMedia +
                ", cnpj='" + cnpj + '\'' +
                ", comentarios=" + Arrays.toString(comentarios) +
                '}';
    }
}