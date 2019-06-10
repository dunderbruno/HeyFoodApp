package com.heyfood.heyfoodapp.infra.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;

import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>{


public static class RestauranteViewHolder extends RecyclerView.ViewHolder {
    RecyclerView recyclerView;
    TextView restauranteNome;
    TextView restauranteCNPJ;

    RestauranteViewHolder(View itemView) {
        super(itemView);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.listaDeRestaurantesId);
        restauranteNome = (TextView) itemView.findViewById(R.id.restaurante_name);
        restauranteCNPJ = (TextView) itemView.findViewById(R.id.restaurante_cnpj);

    }
}

    List<Restaurante> restaurantes;

    public RestauranteAdapter(List<Restaurante> restaurantes){
        this.restaurantes = restaurantes;
    }


    @Override
    public int getItemCount() {
        return restaurantes.size();
    }

    @Override
    public RestauranteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_listar_restaurantes, viewGroup, false);
        RestauranteViewHolder rvh = new RestauranteViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RestauranteViewHolder personViewHolder, int i) {
        personViewHolder.restauranteNome.setText(restaurantes.get(i).getNome());
        personViewHolder.restauranteCNPJ.setText(restaurantes.get(i).getCnpj());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        }
}

