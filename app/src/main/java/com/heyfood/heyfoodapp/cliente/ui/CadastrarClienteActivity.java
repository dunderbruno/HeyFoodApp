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
        boolean campoNome = nome.getText().toString().length() != 0;
        boolean campoLogin = login.getText().toString().length() != 0;
        boolean campoSenha = senha.getText().toString().length() != 0;
        boolean campoDataNascimento = dataNascimento.getText().toString().length() != 0;
        boolean campoCpf = cpf.getText().toString().length() != 0;

        return campoNome && campoLogin && campoSenha && campoDataNascimento && campoCpf;
    }


    public void cadastrar(View view) {
        if(!validarCampos()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
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