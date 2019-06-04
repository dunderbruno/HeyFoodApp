package com.heyfood.heyfoodapp.infra.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.proprietario.ui.CadastrarProprietarioActivity;
import com.heyfood.heyfoodapp.restaurante.ui.CadastrarRestauranteActivity;

public class MainProprietarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_proprietario);

    }

    public void abrirTelaCadastroRestaurante(View view){
        Intent novaTela = new Intent(this, CadastrarRestauranteActivity.class);
        startActivity(novaTela);
    }
}
