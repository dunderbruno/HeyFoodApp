package com.heyfood.heyfoodapp.proprietario.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;


import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;
import com.heyfood.heyfoodapp.util.RecyclerItemClickListener;

import java.util.List;

public class ListarRestaurantesProprietario extends AppCompatActivity {
    private RecyclerView recyclerRestauranteProprietario;
    private RestauranteDAO restauranteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_restaurantes_proprietario);

        restauranteDAO = new RestauranteDAO(this);

        recyclerRestauranteProprietario = findViewById(R.id.recyclerRestauranteProprietario);

        //Configurar adapter
        final List<Restaurante> listaRestaurante = restauranteDAO.getListaRestaurantes();
        AdapterRestauranteProprietario adapter = new AdapterRestauranteProprietario( listaRestaurante );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerRestauranteProprietario.setLayoutManager(layoutManager);
        recyclerRestauranteProprietario.setHasFixedSize(true);
        recyclerRestauranteProprietario.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerRestauranteProprietario.setAdapter( adapter );

        recyclerRestauranteProprietario.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerRestauranteProprietario,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Restaurante restaurante = listaRestaurante.get(position);
                                StringBuilder mensagem = new StringBuilder();
                                mensagem.append("Endereço: \n");
                                mensagem.append(restaurante.getEndereco().getRua() + ", " + restaurante.getEndereco().getNumero() + "\n");
                                mensagem.append(restaurante.getEndereco().getBairro() + "\n");
                                mensagem.append(restaurante.getEndereco().getCidade() + "\n\n");
                                mensagem.append("Fone: " + restaurante.getContato().getTelefone() + "\n");
                                mensagem.append("Email: " + restaurante.getContato().getEmail() + "\n");
                                mensagem.append(restaurante.getContato().getSite());

                                AlertDialog.Builder dialog = new AlertDialog.Builder(ListarRestaurantesProprietario.this);
                                //Configura titulo e mensagem
                                dialog.setTitle(restaurante.getNome());
                                dialog.setMessage(mensagem.toString());
                                dialog.setCancelable(true);
                                //Configura acoes para o botao
                                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                                //Criar e exbir o alertDialog
                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }
}
