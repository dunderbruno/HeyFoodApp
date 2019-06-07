package com.heyfood.heyfoodapp.cliente.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.proprietario.ui.HomeProprietarioActivity;
import com.heyfood.heyfoodapp.categoria.dominio.Categoria;
import com.heyfood.heyfoodapp.cliente.negocio.ClienteServices;


public class PreferenciaClienteActivity extends AppCompatActivity {
    Cliente cliente;

    CheckBox acai;
    CheckBox brasileira;
    CheckBox carnes;
    CheckBox chinesa;
    CheckBox conteporanea;
    CheckBox italiana;
    CheckBox japonesa;
    CheckBox lanches;
    CheckBox marmita;
    CheckBox pizza;
    CheckBox saudavel;
    CheckBox alacarte;
    CheckBox rodizio;
    CheckBox selfservice;
    CheckBox delivery;

    private final ClienteServices services = new ClienteServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencia_cliente);

        acai = findViewById(R.id.clienteAcaiId);
        brasileira = findViewById(R.id.clienteBrasileiraId);
        carnes = findViewById(R.id.clienteCarnesId);
        chinesa = findViewById(R.id.clienteChinesaId);
        conteporanea = findViewById(R.id.clienteContemporaneaId);
        italiana = findViewById(R.id.clienteItalianaId);
        japonesa = findViewById(R.id.clienteJaponesaId);
        lanches = findViewById(R.id.clienteLanchesId);
        marmita = findViewById(R.id.clienteMarmitaId);
        pizza = findViewById(R.id.clientePizzaId);
        saudavel = findViewById(R.id.clienteSaudavelId);
        alacarte = findViewById(R.id.clienteAlacarteId);
        rodizio = findViewById(R.id.clienteRodizioId);
        selfservice = findViewById(R.id.clienteSelfserviceId);
        delivery = findViewById(R.id.clienteDeliveryId);

        cliente = Sessao.instance.getCliente();
    }

    public Categoria createCategoria(){
        Categoria categoria = new Categoria();
        if(acai.isChecked()){
            categoria.setAcai(true);
        }
        else{
            categoria.setAcai(false);

        }
        if(brasileira.isChecked()){
            categoria.setBrasileira(true);
        }
        else{
            categoria.setBrasileira(false);

        }
        if(carnes.isChecked()){
            categoria.setCarnes(true);
        }
        else{
            categoria.setCarnes(false);
        }
        if(chinesa.isChecked()){
            categoria.setChinesa(true);
        }
        else{
            categoria.setChinesa(false);
        }
        if(conteporanea.isChecked()){
            categoria.setContemporanea(true);
        }
        else{
            categoria.setContemporanea(false);
        }
        if(italiana.isChecked()){
            categoria.setItaliana(true);
        }
        else{
            categoria.setItaliana(false);
        }
        if(japonesa.isChecked()){
            categoria.setJaponesa(true);
        }
        else{
            categoria.setJaponesa(false);
        }
        if(lanches.isChecked()){
            categoria.setLanches(true);
        }
        else{
            categoria.setLanches(false);
        }
        if(marmita.isChecked()){
            categoria.setMarmita(true);
        }
        else{
            categoria.setMarmita(false);
        }
        if(pizza.isChecked()){
            categoria.setPizza(true);
        }
        else{
            categoria.setPizza(false);
        }
        if(saudavel.isChecked()){
            categoria.setSaudavel(true);
        }
        else{
            categoria.setSaudavel(false);
        }
        if(alacarte.isChecked()){
            categoria.setAlacarte(true);
        }
        else{
            categoria.setAlacarte(false);
        }
        if(rodizio.isChecked()){
            categoria.setRodizio(true);
        }
        else{
            categoria.setRodizio(false);

        }
        if(selfservice.isChecked()){
            categoria.setSelfservice(true);
        }
        else{
            categoria.setSelfservice(false);
        }
        if(delivery.isChecked()){
            categoria.setDelivery(true);
        }
        else{
            categoria.setDelivery(false);
        }

        return categoria;
    }


    public void finalizarCadastroRestaurante(View view){
        try{
            cliente.setPreferencias(createCategoria());
            Sessao.instance.setCliente(cliente);
            services.cadastrar(cliente);

            Intent novaTela = new Intent(this, HomeProprietarioActivity.class);
            startActivity(novaTela);
            Toast.makeText(this, "Restaurante cadastrado com sucesso", Toast.LENGTH_LONG).show();
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
            Toast.makeText(this, "Erro ao cadastrar restaurante", Toast.LENGTH_LONG).show();
        }

    }
}
