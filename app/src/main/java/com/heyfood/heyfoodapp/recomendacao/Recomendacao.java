package com.heyfood.heyfoodapp.recomendacao;

import android.content.Context;

import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;

import java.util.List;

/**
 * Created by ANDRE.CSASSIS on 26/06/2019.
 */

public class Recomendacao {
    Cliente cliente;

    public Recomendacao(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Restaurante> getRestaurantes(Context context){
        String cidade = cliente.getUsuario().getPessoa().getEndereco().getCidade();
        RestauranteDAO restauranteDAO = new RestauranteDAO(context);
        List<Restaurante> listaRestaurante = restauranteDAO.getRestaurantesByCidade(cidade);
        return listaRestaurante;
    }

}
