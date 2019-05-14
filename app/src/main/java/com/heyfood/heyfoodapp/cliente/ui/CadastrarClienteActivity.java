package com.heyfood.heyfoodapp.cliente.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.infra.ui.MaskEditUtil;

public class CadastrarClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        //Inicializando objetos dos componentes de Layout
        EditText campoData = findViewById(R.id.campoDataId);
        EditText campoCpf = findViewById(R.id.campoCpfId);

        //Configurando mascara dos campos de Data e CPF
        campoData.addTextChangedListener(MaskEditUtil.mask(campoData, MaskEditUtil.FORMAT_DATE));
        campoCpf.addTextChangedListener(MaskEditUtil.mask(campoCpf, MaskEditUtil.FORMAT_CPF));
    }
}
