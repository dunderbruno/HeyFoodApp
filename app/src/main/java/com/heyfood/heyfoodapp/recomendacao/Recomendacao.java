package com.heyfood.heyfoodapp.recomendacao;

import android.content.Context;

import com.heyfood.heyfoodapp.categoria.dominio.Categoria;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Recomendacao {
    private Cliente cliente;
    private int pesoCategoria = 0;
    private List<Restaurante> listaRestaurante;

    public Recomendacao(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Restaurante> getRestaurantes(Context context){
        String cidade = cliente.getUsuario().getPessoa().getEndereco().getCidade();
        RestauranteDAO restauranteDAO = new RestauranteDAO(context);
        listaRestaurante = restauranteDAO.getRestaurantesByCidade(cidade);
        return listaRestaurante;
    }

    public void setRank(){
        for(Restaurante restaurante: listaRestaurante){
            if(restaurante.getEspecialidades().getAcai() && cliente.getPreferencias().getAcai()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getBrasileira() && cliente.getPreferencias().getBrasileira()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getCarnes() && cliente.getPreferencias().getCarnes()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getChinesa() && cliente.getPreferencias().getChinesa()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getContemporanea() && cliente.getPreferencias().getContemporanea()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getItaliana() && cliente.getPreferencias().getItaliana()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getJaponesa() && cliente.getPreferencias().getJaponesa()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getLanches() && cliente.getPreferencias().getLanches()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getMarmita() && cliente.getPreferencias().getMarmita()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getPizza() && cliente.getPreferencias().getPizza()){
                pesoCategoria += 1;
            }
            if(restaurante.getEspecialidades().getSaudavel() && cliente.getPreferencias().getSaudavel()){
                pesoCategoria += 1;
            }
            restaurante.setPeso(pesoCategoria);
            pesoCategoria = 0;
        }
        ordenaLista();

    }

    public void ordenaLista(){
        Collections.sort(listaRestaurante, new Comparator<Restaurante>() {
            @Override
            public int compare(Restaurante r1, Restaurante r2) {
                int peso1 = r1.getPeso();
                int peso2 = r2.getPeso();
                return peso1 < peso2 ? -1 : (peso1 > peso2 ? +1 : 0);
            }
        });
    }

}
