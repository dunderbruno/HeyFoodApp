package com.heyfood.heyfoodapp.usuario.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.ui.CadastrarCliente;
import com.heyfood.heyfoodapp.infra.ui.MainActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void abrirTelaCadastroCliente(View view){
        Intent novaTela = new Intent(this, CadastrarCliente.class);
        startActivity(novaTela);
    }

    public void abrirTelaPrincipal(View view) {
        EditText campoEmail = findViewById(R.id.campoEmailId);
        EditText campoSenha = findViewById(R.id.campoSenhaId);

        if (campoEmail.getText().toString().trim().equals("") || campoSenha.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Preencha os campos", Toast.LENGTH_LONG).show();
        } else {
            Intent novaTela = new Intent(this, MainActivity.class);
            startActivity(novaTela);
        }

    }

}
