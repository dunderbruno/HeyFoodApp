package com.heyfood.heyfoodapp.cliente.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.usuario.negocio.UsuarioServices;
import com.heyfood.heyfoodapp.usuario.ui.LoginActivity;
import com.heyfood.heyfoodapp.util.MaskEditUtil;
import com.heyfood.heyfoodapp.pessoa.dominio.Pessoa;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText login;
    private EditText senha;
    private EditText dataNascimento;
    private EditText cpf;

    private final UsuarioServices services = new UsuarioServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        //Inicializando objetos dos componentes de Layout
        nome = findViewById(R.id.textNomeId);
        login = findViewById(R.id.campoLoginId);
        senha = findViewById(R.id.campoSenhaId);
        dataNascimento = findViewById(R.id.campoDataId);
        cpf = findViewById(R.id.campoCpfId);

        //Configurando mascara dos campos de Data e CPF
        dataNascimento.addTextChangedListener(MaskEditUtil.mask(dataNascimento, MaskEditUtil.FORMAT_DATE));
        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));
    }
    private boolean validarCpf(){

        String campoCpf = this.cpf.getText().toString().replace(".","");
        campoCpf = campoCpf.replace("-","");
        //int cpf = Integer.parseInt(campoCpf);
        int soma = 0;
        for (int i=10, j=0 ; i>1 ; i--, j++){
            soma += Integer.parseInt(campoCpf.substring(j, j+1)) * i;
        }
        if (!((soma*10)%11 == Integer.parseInt(campoCpf.substring(10, 11)))){
            return false;
        }
        soma = 0;
        for (int i=11, j=0 ; i>1 ; i--, j++) {
            soma += Integer.parseInt(campoCpf.substring(j, j + 1)) * i;
        }
        if (!((soma*10)%11 == Integer.parseInt(campoCpf.substring(11, 12)))){
            return false;
        }
        return true;

    }

    private Pessoa createPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome.getText().toString());
        pessoa.setDataNAscimento(dataNascimento.getText().toString());
        pessoa.setCpf(cpf.getText().toString());
        return pessoa;
    }

    private Usuario createUsuario(){
        Usuario usuario = new Usuario();
        usuario.setPessoa(createPessoa());
        usuario.setLogin(login.getText().toString());
        usuario.setSenha(senha.getText().toString());
        return usuario;
    }

    private boolean validarCampos(){
        return
                nome.getText().toString().length() != 0 &&
        login.getText().toString().length() != 0 &&
        senha.getText().toString().length() != 0 &&
        dataNascimento.getText().toString().length() != 0 &&
        cpf.getText().toString().length() == 14;

    }


    public void cadastrar(View view) {
        if(!validarCampos()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
            return;
        }
        if (validarCpf()){
            Toast.makeText(this, "CPF inválido.", Toast.LENGTH_LONG).show();
            return;
        }
        Usuario usuario = createUsuario();
        try{
            services.cadastrar(usuario);
            Intent novaTela = new Intent(this, LoginActivity.class);
            startActivity(novaTela);
        }
        catch (Exception e){
            Toast.makeText(this, "Este login jรก existe", Toast.LENGTH_LONG).show();
        }

    }

}