package com.heyfood.heyfoodapp.cliente.ui;

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
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;
import com.heyfood.heyfoodapp.util.RecyclerItemClickListener;

import java.util.List;

public class ListarRestaurantesActivity extends AppCompatActivity {
    private RecyclerView recyclerRestaurante;
    private RestauranteDAO restauranteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_restaurantes);

        restauranteDAO = new RestauranteDAO(this);

        recyclerRestaurante = findViewById(R.id.recyclerRestaurante);

        //Configurar adapter
        final List<Restaurante> listaRestaurante = restauranteDAO.getListaRestaurantes();
        AdapterRestaurante adapter = new AdapterRestaurante( listaRestaurante );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerRestaurante.setLayoutManager(layoutManager);
        recyclerRestaurante.setHasFixedSize(true);
        recyclerRestaurante.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerRestaurante.setAdapter( adapter );

        recyclerRestaurante.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerRestaurante,
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

                                AlertDialog.Builder dialog = new AlertDialog.Builder(ListarRestaurantesActivity.this);
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
