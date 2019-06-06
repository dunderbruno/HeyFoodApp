package com.heyfood.heyfoodapp.cliente.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;

public class PreferenciaClienteActivity extends AppCompatActivity {
    Restaurante restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencia_cliente);

        restaurante = Sessao.instance.getRestaurante();
    }
}
