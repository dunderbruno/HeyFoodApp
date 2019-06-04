package com.heyfood.heyfoodapp.proprietario.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.proprietario.dominio.Proprietario;
import com.heyfood.heyfoodapp.restaurante.ui.CadastrarRestauranteActivity;

public class HomeProprietarioActivity extends AppCompatActivity {

    TextView nome;
    Proprietario proprietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_proprietario);
        proprietario = Sessao.instance.getProprietario();

        nome = findViewById(R.id.textBoasVindasProprietarioId);
        nome.setText(String.format("Bem vindo, %s!", proprietario.getUsuario().getPessoa().getNome()));
    }

    public void abrirTelaCadastroRestaurante(View view){
        Intent novaTela = new Intent(this, CadastrarRestauranteActivity.class);
        startActivity(novaTela);


    }
}