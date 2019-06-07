package com.heyfood.heyfoodapp.proprietario.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.contato.dominio.Contato;
import com.heyfood.heyfoodapp.endereco.dominio.Endereco;
import com.heyfood.heyfoodapp.pessoa.dominio.Pessoa;
import com.heyfood.heyfoodapp.proprietario.dominio.Proprietario;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;
import com.heyfood.heyfoodapp.usuario.ui.LoginActivity;
import com.heyfood.heyfoodapp.util.MaskEditUtil;
import com.heyfood.heyfoodapp.proprietario.negocio.ProprietarioServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastrarProprietarioActivity extends AppCompatActivity {
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

    private final ProprietarioServices services = new ProprietarioServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_proprietario);

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

        dataNascimento.addTextChangedListener(MaskEditUtil.mask(dataNascimento, MaskEditUtil.FORMAT_DATE));
        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));
        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));
        cep.addTextChangedListener(MaskEditUtil.mask(cep, MaskEditUtil.FORMAT_CEP));
    }
    private boolean validarCpf(){
        // Verifica se o campo está preenchido com 14 digitos
        //incluindo pontos e o ífem
        if (this.cpf.getText().toString().length()<14){
            return false;
        }
        // Remove os pontos
        String campoCpf = this.cpf.getText().toString().replace(".", "");
        // Remove o ífem
        campoCpf = campoCpf.replace("-","");
        //int cpf = Integer.parseInt(campoCpf);
        int soma = 0;
        // VERIFICAÇÃO DO PENÚLTIMO DÍGITO
        // Multiplica os digitos por 10, 9, 8,...,2
        // Cada digito é multiplicado por um valor e cada resultudO é somado
        for (int i=10, j=0 ; i>1 ; i--, j++){
            soma += Integer.parseInt(campoCpf.substring(j, j+1)) * i;
        }
        // Calcular o resto da divisão
        // Se for igual a 10, o resto será 0
        if((soma*10)%11 == 10){
            soma = 0;
        }
        // Verificar se o resto da divisão é igual ao penúltimo dígito
        if (!((soma*10)%11 == Integer.parseInt(campoCpf.substring(9,10)))){
            return false;
        }
        soma = 0;
        // VERIFICAÇÃO DO ÚLTIMO DÍGITO
        // Multiplica os digitos por 11, 10, 9,...,2
        // Cada digito é multiplicado por um valor e cada resultudO é somado
        for (int i=11, j=0 ; i>1 ; i--, j++) {
            soma += Integer.parseInt(campoCpf.substring(j, j + 1)) * i;
        }
        // Calcular o resto da divisão
        // Se for igual a 10, o resto será 0
        if((soma*10)%11 == 10) {
            soma = 0;
        }
        // Verificar se o resto da divisão é igual ao último dígito
        if (!((soma*10)%11 == Integer.parseInt(campoCpf.substring(10)))){
            return false;
        }

        if (campoCpf.equals("00000000000") || campoCpf.equals("11111111111") ||
                campoCpf.equals("22222222222") || campoCpf.equals("33333333333") ||
                campoCpf.equals("44444444444") || campoCpf.equals("55555555555") ||
                campoCpf.equals("66666666666") || campoCpf.equals("77777777777") ||
                campoCpf.equals("88888888888") || campoCpf.equals("99999999999")){
            return false;}
        return true;

    }

    private boolean validarEmail(){
        String email = login.getText().toString(); // Transforma para String
        // Verifica se tem '@' e '.com'
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        return false;
    }

    private boolean validarData() {
        String data = dataNascimento.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); // Formatar datas
        format.setLenient(false); // Quando seta para "falso", datas incorretas não são aceitas

        try {
            Date date = format.parse(data);
            // Se converter o objeto para o tipo Date
            // É porque a data está no formato correto
            return true;
        } catch (ParseException e) {
            return false;

        }
    }

    private Proprietario createProprietario(){
        Proprietario proprietario = new Proprietario();
        proprietario.setUsuario(createUsuario());
        return proprietario;
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
        if (!validarCampos()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
            return;
        }
        if (!validarEmail()) {
            Toast.makeText(this, "Email inválido.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!validarData()) {
            Toast.makeText(this, "Data inválida.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!validarCpf()) {
            Toast.makeText(this, "CPF inválido.", Toast.LENGTH_LONG).show();
            return;
        }
        Proprietario proprietario = createProprietario();

        try{
            services.cadastrar(proprietario);
            Intent novaTela = new Intent(this, LoginActivity.class);
            startActivity(novaTela);
            Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_LONG).show();
        }
        catch (NullPointerException exec) {
            Toast.makeText(this, "Null Pointer Exception", Toast.LENGTH_LONG).show();
        }
        catch (ArrayIndexOutOfBoundsException exec) {
            Toast.makeText(this, "Array Index Out of Bounds", Toast.LENGTH_LONG).show();
        }
        catch (RuntimeException exec) {
            Toast.makeText(this, "Run Time Exception", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Este login já existe", Toast.LENGTH_LONG).show();
        }

    }
}