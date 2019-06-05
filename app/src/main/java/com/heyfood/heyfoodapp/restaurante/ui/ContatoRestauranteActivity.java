package com.heyfood.heyfoodapp.restaurante.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.util.MaskEditUtil;

public class ContatoRestauranteActivity extends AppCompatActivity {
    private EditText telefone;
    private EditText email;
    private EditText site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_restaurante);

        telefone = findViewById(R.id.campoNomeId);
        email = findViewById(R.id.campoEmailId);
        site = findViewById(R.id.campoSiteId);

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
    }
}
