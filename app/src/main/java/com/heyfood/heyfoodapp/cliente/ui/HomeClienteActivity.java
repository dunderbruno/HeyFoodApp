package com.heyfood.heyfoodapp.cliente.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.infra.Sessao;

public class HomeClienteActivity extends AppCompatActivity {

    TextView nome;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);

        cliente = Sessao.instance.getCliente();

        nome = findViewById(R.id.textBoasVindasId);
        //Bem vindo 'nome do usuario'
        nome.setText(String.format("Bem vindo, %s!", cliente.getUsuario().getPessoa().getNome()));
    }
}
