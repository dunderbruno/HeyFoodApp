package com.heyfood.heyfoodapp.cliente.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;

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
        AdapterRestaurante adapter = new AdapterRestaurante( restauranteDAO.getListaRestaurantes() );

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerRestaurante.setLayoutManager(layoutManager);
        recyclerRestaurante.setHasFixedSize(true);
        recyclerRestaurante.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerRestaurante.setAdapter( adapter );
    }
}
