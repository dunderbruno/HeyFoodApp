package com.heyfood.heyfoodapp.cliente.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.infra.Sessao;

public class PerfilClienteActivity extends AppCompatActivity {

    private TextView nome;
    private TextView email;
    private TextView cpf;
    private TextView nascimento;
    private TextView telefone;
    private TextView cep;
    private TextView rua;
    private TextView numero;
    private TextView bairro;
    private TextView cidade;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        cliente = Sessao.instance.getCliente();

        nome = findViewById(R.id.nomeClienteId);
        email = findViewById(R.id.emailClienteId);
        cpf = findViewById(R.id.cpfClienteId);
        nascimento = findViewById(R.id.nascimentoClienteId);
        telefone = findViewById(R.id.telefoneClienteId);
        cep = findViewById(R.id.cepClienteId);
        rua = findViewById(R.id.ruaClienteId);
        numero = findViewById(R.id.numeroClienteId);
        bairro = findViewById(R.id.bairroClienteId);
        cidade = findViewById(R.id.cidadeClienteId);

        nome.setText(String.format("Nome: %s",cliente.getUsuario().getPessoa().getNome()));
        email.setText(String.format("email: %s",cliente.getUsuario().getLogin()));
        cpf.setText(String.format("CPF: %s",cliente.getUsuario().getPessoa().getCpf()));
        nascimento.setText(String.format("Dada de nascimento: %s",cliente.getUsuario().getPessoa().getDataNascimento()));
        telefone.setText(String.format("Telefone: %s",cliente.getUsuario().getPessoa().getContato().getTelefone()));
        cep.setText(String.format("cep: %s",cliente.getUsuario().getPessoa().getEndereco().getCep()));
        rua.setText(String.format("rua: %s",cliente.getUsuario().getPessoa().getEndereco().getRua()));
        numero.setText(String.format("numero: %s",cliente.getUsuario().getPessoa().getEndereco().getNumero()));
        bairro.setText(String.format("bairro: %s",cliente.getUsuario().getPessoa().getEndereco().getBairro()));
        cidade.setText(String.format("cidade: %s",cliente.getUsuario().getPessoa().getEndereco().getCidade()));

    }


}
