package com.heyfood.heyfoodapp.recomendacao;

import android.content.Context;
import android.support.annotation.NonNull;

import com.heyfood.heyfoodapp.avaliacao.dominio.AvaliacaoRestaurante;
import com.heyfood.heyfoodapp.avaliacao.persistencia.AvaliacaoRestauranteDAO;
import com.heyfood.heyfoodapp.categoria.dominio.Categoria;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.cliente.persistencia.ClienteDAO;
import com.heyfood.heyfoodapp.cliente.ui.HomeClienteActivity;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.prato.ui.ListarPratosActivity;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Recomendacao {
    private ClienteDAO clienteDAO = new ClienteDAO(HomeClienteActivity.contexto);
    private RestauranteDAO restauranteDAO = new RestauranteDAO(HomeClienteActivity.contexto);
    private AvaliacaoRestauranteDAO avaliacaoRestauranteDAO = new AvaliacaoRestauranteDAO(HomeClienteActivity.contexto);
    private Map<Cliente,HashMap<Restaurante,Float>> usersMatrix;
    private List<Restaurante> listaRestaurantes;
    private List<Restaurante> listaRestaurantesRecomendados;
    Map<Cliente, HashMap<Restaurante, Float>> predicao;

    public Recomendacao(){
        listaRestaurantes = restauranteDAO.getListaRestaurantes();
        usersMatrix = criaMatrizCliente();
        predicao = SlopeOne.slopeOne(usersMatrix, listaRestaurantes);
        listaRestaurantesRecomendados = getOrderList(getRecomendacao());
    }

    public Map<Cliente,HashMap<Restaurante,Float>> criaMatrizCliente(){
        Map<Cliente,HashMap<Restaurante,Float>> matrizClientes = new HashMap<>();
        List<Cliente> listaClientes = clienteDAO.getListaClientes();
        for(Cliente cliente: listaClientes){
            matrizClientes.put(cliente, criaMatrizRestaurante(cliente.getId()));
        }
        return matrizClientes;
    }

    private HashMap<Restaurante,Float> criaMatrizRestaurante(long idCliente){
        HashMap<Restaurante,Float> matrizRestaurante = new HashMap<>();
        for(Restaurante restaurante: listaRestaurantes){
            Float avaliacaoRestaurante = avaliacaoRestauranteDAO.getAvaliacao(idCliente, restaurante.getId());
            if(avaliacaoRestaurante != -11.0f){
                matrizRestaurante.put(restaurante, avaliacaoRestauranteDAO.getAvaliacao(idCliente, restaurante.getId()));
            }
        }
        return matrizRestaurante;
    }

    private List<Avaliacao> getRecomendacao(){
        List<Avaliacao> predicoes = new ArrayList<>();
        Cliente cliente = Sessao.instance.getCliente();
        HashMap<Restaurante, Float> avaliacoes = predicao.get(cliente);
        //HashMap<Restaurante, Float> predicoes = new HashMap<>();
//        if(avaliacoes!=null){
//            for(Map.Entry r: avaliacoes.entrySet()){
//                Restaurante restaurante = (Restaurante) r.getKey();
//                Float avaliacao = avaliacaoRestauranteDAO.getAvaliacao(cliente.getId(), restaurante.getId());
//                if (avaliacao==null){
//                    predicoes.add(new Avaliacao(restaurante,(Float) r.getValue()));
//                }
//            }
//        }
        for(Map.Entry r: avaliacoes.entrySet()){
            Restaurante restaurante = (Restaurante) r.getKey();
            Float avaliacao = avaliacaoRestauranteDAO.getAvaliacao(cliente.getId(), restaurante.getId());
            if (avaliacao==null){
                predicoes.add(new Avaliacao(restaurante,(Float) r.getValue()));
            }
        }
        return predicoes;
    }

    private List<Restaurante> getOrderList(List<Avaliacao> avaliacao){
        Collections.sort(avaliacao, new Comparator<Avaliacao>() {
            @Override
            public int compare(Avaliacao a1, Avaliacao a2) {
                return a1.getNota() < a2.getNota() ? -1 : (a1.getNota() > a2.getNota() ? +1 : 0);
            }
        });
        List<Restaurante> restaurantes = new ArrayList<>();
        for(Avaliacao a: avaliacao){
            restaurantes.add(a.getRestaurante());
        }
        return restaurantes;
    }

    public List<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }

    public List<Restaurante> getListaRestaurantesRecomendados() {
        return listaRestaurantesRecomendados;
    }

    private class Avaliacao{
        private Restaurante restaurante;
        private Float nota;

        public Avaliacao(Restaurante restaurante, Float nota) {
            this.restaurante = restaurante;
            this.nota = nota;
        }

        public Restaurante getRestaurante() {
            return restaurante;
        }

        public void setRestaurante(Restaurante restaurante) {
            this.restaurante = restaurante;
        }

        public Float getNota() {
            return nota;
        }

        public void setNota(Float nota) {
            this.nota = nota;
        }
    }
}
