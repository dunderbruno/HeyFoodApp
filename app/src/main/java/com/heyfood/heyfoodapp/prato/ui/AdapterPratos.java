package com.heyfood.heyfoodapp.prato.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;

import java.util.List;

/**
 * Created by andre.csassis on 27/06/2019.
 */

public class AdapterPratos extends RecyclerView.Adapter<AdapterPratos.MyViewHolder> {
    private List<Restaurante> listaPrato;

    public AdapterPratos(List<Restaurante> listaPrato) {
        this.listaPrato = listaPrato;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeRestaurante;
        TextView bairro;
        TextView telefone;

        public MyViewHolder(View itemView){
            super(itemView);
            nomeRestaurante = itemView.findViewById(R.id.textNomeRestauranteId);
            bairro = itemView.findViewById(R.id.textBairroRestauranteId);
            telefone = itemView.findViewById(R.id.textTelefoneRestauranteId);
        }
    }
}
