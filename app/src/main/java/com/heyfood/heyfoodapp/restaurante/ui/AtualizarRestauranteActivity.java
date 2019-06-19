package com.heyfood.heyfoodapp.restaurante.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.util.MaskEditUtil;

public class AtualizarRestauranteActivity extends AppCompatActivity {

    EditText nome, cep, rua, numero, bairro, cidade;
    Restaurante restaurante = Sessao.instance.getRestaurante();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_restaurante);

        nome = findViewById(R.id.campoNomeId);
        cep = findViewById(R.id.campoCepId);
        rua = findViewById(R.id.campoRuaId);
        numero = findViewById(R.id.campoNumeroId);
        bairro = findViewById(R.id.campoBairroId);
        cidade = findViewById(R.id.campoCidadeId);

        nome.setText(restaurante.getNome());
        cep.setText(restaurante.getEndereco().getCep());
        rua.setText(restaurante.getEndereco().getRua());
        numero.setText(restaurante.getEndereco().getNumero());
        bairro.setText(restaurante.getEndereco().getBairro());
        cidade.setText(restaurante.getEndereco().getCidade());

        cep.addTextChangedListener(MaskEditUtil.mask(cep, MaskEditUtil.FORMAT_CEP));

    }



}
