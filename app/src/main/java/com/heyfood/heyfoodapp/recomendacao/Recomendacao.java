package com.heyfood.heyfoodapp.recomendacao;

import android.content.Context;
import android.support.annotation.NonNull;

import com.heyfood.heyfoodapp.avaliacao.dominio.AvaliacaoRestaurante;
import com.heyfood.heyfoodapp.avaliacao.persistencia.AvaliacaoRestauranteDAO;
import com.heyfood.heyfoodapp.categoria.dominio.Categoria;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.cliente.persistencia.ClienteDAO;
import com.heyfood.heyfoodapp.cliente.ui.HomeClienteActivity;
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
    private  List<Restaurante> listaRestaurantes;

    int maxItemsId = 0;
    float mRatings[][];
    int mFreq[][];

    public Recomendacao(){
        usersMatrix = criaMatrizCliente();
        listaRestaurantes = restauranteDAO.getListaRestaurantes();
//        predict();
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
            if(avaliacaoRestaurante != null){
                matrizRestaurante.put(restaurante, avaliacaoRestauranteDAO.getAvaliacao(idCliente, restaurante.getId()));
            }
        }
        return matrizRestaurante;
    }

    public List<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }
}
