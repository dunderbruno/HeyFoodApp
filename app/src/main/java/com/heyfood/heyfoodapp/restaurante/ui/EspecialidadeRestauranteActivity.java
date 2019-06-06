package com.heyfood.heyfoodapp.restaurante.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.negocio.ClienteServices;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.proprietario.ui.HomeProprietarioActivity;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.negocio.RestauranteServices;
import com.heyfood.heyfoodapp.usuario.ui.LoginActivity;

public class EspecialidadeRestauranteActivity extends AppCompatActivity {
    Restaurante restaurante;

    private final RestauranteServices services = new RestauranteServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidade_restaurante);

        restaurante = Sessao.instance.getRestaurante();
    }

    public void finalizarCadastroRestaurante(View view){
        try{
            services.cadastrar(restaurante);

            Intent novaTela = new Intent(this, HomeProprietarioActivity.class);
            startActivity(novaTela);
            Toast.makeText(this, "Restaurante cadastrado com sucesso", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Erro ao cadastrar restaurante", Toast.LENGTH_LONG).show();
        }

    }
}
