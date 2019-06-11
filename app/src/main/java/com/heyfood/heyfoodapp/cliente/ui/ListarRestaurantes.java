package com.heyfood.heyfoodapp.cliente.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.infra.ui.RestauranteAdapter;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;

import com.heyfood.heyfoodapp.restaurante.negocio.RestauranteServices;

import java.util.List;


public class ListarRestaurantes extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    private final RestauranteServices services = new RestauranteServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_restaurantes);
        recyclerView = (RecyclerView) findViewById(R.id.listaDeRestaurantesId);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<Restaurante> myDataset = services.getRestaurantes();
        mAdapter = new RestauranteAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
}
