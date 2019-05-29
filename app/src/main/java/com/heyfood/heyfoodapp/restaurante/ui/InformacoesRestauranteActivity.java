package com.heyfood.heyfoodapp.restaurante.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import com.heyfood.heyfoodapp.util.MaskEditUtil;
import com.heyfood.heyfoodapp.R;

public class InformacoesRestauranteActivity extends AppCompatActivity {
    private EditText abre;
    private EditText fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_restaurante);
        abre = findViewById(R.id.horaAbreId);
        fecha = findViewById(R.id.horaFechaId);

        abre.addTextChangedListener(MaskEditUtil.mask(abre, MaskEditUtil.FORMAT_HOUR));
        fecha.addTextChangedListener(MaskEditUtil.mask(fecha, MaskEditUtil.FORMAT_HOUR));

//        private boolean validarHora(){
//        }


    }
}
    

