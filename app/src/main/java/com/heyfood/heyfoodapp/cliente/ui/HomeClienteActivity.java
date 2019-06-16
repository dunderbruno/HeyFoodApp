package com.heyfood.heyfoodapp.cliente.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.cliente.negocio.ClienteServices;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.restaurante.ui.ListarRestaurantes;
import com.heyfood.heyfoodapp.usuario.ui.LoginActivity;

public class HomeClienteActivity extends AppCompatActivity {

    TextView nome;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);

        cliente = Sessao.instance.getCliente();

        //Bem vindo 'nome do usuario'
        nome = findViewById(R.id.textBoasVindasClienteId);
        nome.setText(String.format("Bem vindo, %s!", cliente.getUsuario().getPessoa().getNome()));
    }

    public void cadastrarPreferencias(View view){
        Intent novaTela = new Intent(this, PreferenciaClienteActivity.class);
        startActivity(novaTela);
    }

    public void verPerfil(View view) {
        Intent novaTela = new Intent(this, PerfilClienteActivity.class);
        startActivity(novaTela);
    }

    public void listarRestaurantes(View view) {
        Intent novaTela = new Intent (this, ListarRestaurantes.class);
        startActivity(novaTela);
    }

    public void logout(View view){
        ClienteServices clienteServices = new ClienteServices(this);
        clienteServices.logout();
        Intent novaTela = new Intent(this, LoginActivity.class);
        startActivity(novaTela);
    }

}
