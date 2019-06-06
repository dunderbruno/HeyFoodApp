package com.heyfood.heyfoodapp.restaurante.negocio;

import android.content.Context;

import com.heyfood.heyfoodapp.categoria.persistencia.EspecialidadeDAO;
import com.heyfood.heyfoodapp.contato.persistencia.ContatoDAO;
import com.heyfood.heyfoodapp.endereco.persistencia.EnderecoDAO;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;

/**
 * Created by andre.csassis on 05/06/2019.
 */

public class RestauranteServices {
    private RestauranteDAO restauranteDAO;
    private ContatoDAO contatoDAO;
    private EnderecoDAO enderecoDAO;
    private EspecialidadeDAO especialidadeDAO;

    public RestauranteServices(Context context){
        restauranteDAO = new RestauranteDAO(context);
        contatoDAO = new ContatoDAO(context);
        enderecoDAO = new EnderecoDAO(context);
        especialidadeDAO = new EspecialidadeDAO(context);
    }

    public void cadastrar(Restaurante restaurante) {
        /* Validação se já existe pelo cnpj
        if (usuarioDAO.getUsuario(cliente.getUsuario().getLogin()) != null) {
            throw new Exception();
        }
        */
        int idContato = contatoDAO.cadastrar(restaurante.getContato());
        restaurante.getContato().setId(idContato);

        int idEndereco = enderecoDAO.cadastrar(restaurante.getEndereco());
        restaurante.getEndereco().setId(idEndereco);

        int idEspecialidades = especialidadeDAO.cadastrar(restaurante.getEspecialidades());
        restaurante.getEspecialidades().setId(idEspecialidades);

        int idRestaurante = restauranteDAO.cadastrar(restaurante);
        restaurante.setId(idRestaurante);


    }

}
