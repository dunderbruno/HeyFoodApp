package com.heyfood.heyfoodapp.cliente.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.cliente.negocio.ClienteServices;
import com.heyfood.heyfoodapp.contato.dominio.Contato;
import com.heyfood.heyfoodapp.endereco.dominio.Endereco;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.usuario.ui.LoginActivity;
import com.heyfood.heyfoodapp.util.MaskEditUtil;
import com.heyfood.heyfoodapp.pessoa.dominio.Pessoa;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;
import com.heyfood.heyfoodapp.infra.negocio.Valida;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText login;
    private EditText senha;
    private EditText dataNascimento;
    private EditText cpf;
    private EditText telefone;
    private EditText cep;
    private EditText rua;
    private EditText numero;
    private EditText bairro;
    private EditText cidade;

    private final ClienteServices services = new ClienteServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        //Inicializando objetos dos componentes de Layout
        nome = findViewById(R.id.campoNomeId);
        login = findViewById(R.id.campoLoginId);
        senha = findViewById(R.id.campoSenhaId);
        dataNascimento = findViewById(R.id.campoDataId);
        cpf = findViewById(R.id.campoCpfId);
        telefone = findViewById(R.id.campoTelefoneId);
        cep = findViewById(R.id.campoCepId);
        rua = findViewById(R.id.campoRuaId);
        numero = findViewById(R.id.campoNumeroId);
        bairro = findViewById(R.id.campoBairroId);
        cidade = findViewById(R.id.campoCidadeId);

        //Configurando mascara dos campos de Data e CPF
        dataNascimento.addTextChangedListener(MaskEditUtil.mask(dataNascimento, MaskEditUtil.FORMAT_DATE));
        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));
        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
        cep.addTextChangedListener(MaskEditUtil.mask(cep, MaskEditUtil.FORMAT_CEP));

        if (Sessao.instance.getCliente() != null){
            Cliente clienteRestaura = Sessao.instance.getCliente();
            nome.setText(clienteRestaura.getUsuario().getPessoa().getNome());
            dataNascimento.setText(clienteRestaura.getUsuario().getPessoa().getDataNascimento());
            cpf.setText(clienteRestaura.getUsuario().getPessoa().getCpf());
            login.setText(clienteRestaura.getUsuario().getLogin());
            senha.setText(clienteRestaura.getUsuario().getSenha());
            telefone.setText(clienteRestaura.getUsuario().getPessoa().getContato().getTelefone());
            cep.setText(clienteRestaura.getUsuario().getPessoa().getEndereco().getCep());
            rua.setText(clienteRestaura.getUsuario().getPessoa().getEndereco().getRua());
            numero.setText(clienteRestaura.getUsuario().getPessoa().getEndereco().getNumero());
            bairro.setText(clienteRestaura.getUsuario().getPessoa().getEndereco().getBairro());
            cidade.setText(clienteRestaura.getUsuario().getPessoa().getEndereco().getCidade());
        }

    }

    @Override
    public void onBackPressed() {
        Sessao.instance.setCliente(createCliente());
        super.onBackPressed();
    }

    private Pessoa createPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setContato(createContato());
        pessoa.setEndereco(createEndereco());
        pessoa.setNome(nome.getText().toString());
        pessoa.setDataNAscimento(dataNascimento.getText().toString());
        pessoa.setCpf(cpf.getText().toString());
        return pessoa;
    }

    private Cliente createCliente(){
        Cliente cliente = new Cliente();
        cliente.setUsuario(createUsuario());

        return cliente;
    }

    private Usuario createUsuario(){
        Usuario usuario = new Usuario();
        usuario.setPessoa(createPessoa());
        usuario.setLogin(login.getText().toString());
        usuario.setSenha(senha.getText().toString());
        return usuario;
    }

    private Contato createContato(){
        Contato contato = new Contato();
        contato.setTelefone(telefone.getText().toString());
        contato.setEmail(login.getText().toString());
        contato.setSite("");
        return contato;
    }

    private Endereco createEndereco(){
        Endereco endereco = new Endereco();
        endereco.setCep(cep.getText().toString());
        endereco.setRua(rua.getText().toString());
        endereco.setNumero(numero.getText().toString());
        endereco.setBairro(bairro.getText().toString());
        endereco.setCidade(cidade.getText().toString());

        return endereco;
    }

    private boolean validarCampos(){
        return
            nome.getText().toString().length() != 0 &&
            login.getText().toString().length() != 0 &&
            senha.getText().toString().length() != 0 &&
            dataNascimento.getText().toString().length() != 0 &&
            cpf.getText().toString().length() !=0 &&
            telefone.getText().toString().length() != 0 &&
            cep.getText().toString().length() != 0 &&
            rua.getText().toString().length() != 0 &&
            numero.getText().toString().length() != 0 &&
            bairro.getText().toString().length() != 0 &&
            cidade.getText().toString().length() != 0;
    }

    public void cadastrar(View view) {
        if(!validarCampos()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
            return;
        }
        if (!Valida.validarEmail(login.getText().toString())){
            Toast.makeText(this, "Email inválido.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!Valida.validarData(dataNascimento.getText().toString())){
            Toast.makeText(this, "Data inválida.", Toast.LENGTH_LONG).show();
            return;
        }
        //
        if (!Valida.validarCpf(cpf.getText().toString())){
            Toast.makeText(this, "CPF inválido.", Toast.LENGTH_LONG).show();
            return;
        }
        Cliente cliente = createCliente();
        try{
            services.cadastrar(cliente);
            Intent novaTela = new Intent(this, LoginActivity.class);
            startActivity(novaTela);
            Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Este login já existe", Toast.LENGTH_LONG).show();
        }

    }

}