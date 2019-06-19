package com.heyfood.heyfoodapp.cliente.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.categoria.dominio.Categoria;
import com.heyfood.heyfoodapp.cliente.negocio.ClienteServices;


public class PreferenciaClienteActivity extends AppCompatActivity {


    Switch acai;
    Switch brasileira;
    Switch carnes;
    Switch chinesa;
    Switch conteporanea;
    Switch italiana;
    Switch japonesa;
    Switch lanches;
    Switch marmita;
    Switch pizza;
    Switch saudavel;
    Switch alacarte;
    Switch rodizio;
    Switch selfservice;
    Switch delivery;

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


    public void finalizarCadastroPreferencias(View view){
        try{
            Categoria preferencias = createCategoria();
            services.cadastrarPreferencias(preferencias);

            Intent novaTela = new Intent(this, HomeClienteActivity2.class);
            startActivity(novaTela);
            Toast.makeText(this, "Preferencias cadastrado com sucesso", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Erro ao cadastrar preferencias", Toast.LENGTH_LONG).show();
        }

    }
}
