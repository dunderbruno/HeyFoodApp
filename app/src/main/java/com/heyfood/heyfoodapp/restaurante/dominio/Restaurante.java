package com.heyfood.heyfoodapp.restaurante.dominio;

import com.heyfood.heyfoodapp.contato.dominio.Contato;
import com.heyfood.heyfoodapp.endereco.dominio.Endereco;

public class Restaurante {
    private int id;
    private String nome;
    private float notaMedia;
    private String cnpj;
    private Endereco endereco;
    private Contato contato;

    public int getId() {
        return id;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "nome='" + nome + '\'' +
                ", notaMedia=" + notaMedia +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}