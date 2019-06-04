package com.heyfood.heyfoodapp.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String NOME_DB = "heyfood.db";
    public static final int VERSAO = 1;
    public static final String TAG = "SQLite";

    //Tabela Cliente
    public static final String TABELA_CLIENTE = "tb_cliente";
    public static final String CAMPO_ID_CLIENTE = "id";
    public static final String CAMPO_FK_USUARIO_CLIENTE = "fk_usuario";
    public static final String CAMPO_FK_PREFERENCIAS = "fk_preferencias";


    //Tabela Pessoa
    public static final String TABELA_PESSOA = "tb_pessoa";
    public static final String CAMPO_ID_PESSOA = "id";
    public static final String CAMPO_NOME = "nome";
    public static final String CAMPO_CPF = "cpf";
    public static final String CAMPO_DATA_NASCIMENTO = "data_nascimento";
    public static final String CAMPO_FK_ENDERECO = "fk_endereco";
    public static final String CAMPO_FK_CONTATO = "fk_contato";

    //Tabela Usuario
    public static final String TABELA_USUARIO = "tb_usuario";
    public static final String CAMPO_ID_USUARIO = "id";
    public static final String CAMPO_FK_PESSOA = "fk_pessoa";
    public static final String CAMPO_LOGIN = "login";
    public static final String CAMPO_SENHA = "senha";

    //Tabela Endereço
    public static final String TABELA_ENDERECO = "tb_endereco";
    public static final String CAMPO_ID_ENDERECO = "id";
    public static final String CAMPO_CEP = "cep";
    public static final String CAMPO_RUA = "rua";
    public static final String CAMPO_BAIRRO = "bairro";
    public static final String CAMPO_CIDADE= "cidade";
    public static final String CAMPO_NUMERO = "numero";

    //Tabela Contato
    public static final String TABELA_CONTATO = "tb_contato";
    public static final String CAMPO_ID_CONTATO = "id";
    public static final String CAMPO_TELEFONE = "telefone";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_SITE = "site";

    //Tabela Restaurante
    public static final String TABELA_RESTAURANTE = "tb_restaurante";
    public static final String CAMPO_ID_RESTAURANTE = "id";
    public static final String CAMPO_NOME_RESTAURANTE = "nome";
    public static final String CAMPO_CNPJ = "cnpj";
    public static final String CAMPO_NOTA_MEDIA = "nota_media";
    public static final String CAMPO_FK_ENDERECO_RESTAURANTE = "fk_endereco";
    public static final String CAMPO_FK_CONTATO_RESTAURANTE = "fk_contato";
    public static final String CAMPO_FK_ESPECIALIDADES = "fk_especialidades";

    //Tabela Proprietario
    public static final String TABELA_PROPRIETARIO = "tb_proprietario";
    public static final String CAMPO_ID_PROPRIETARIO = "id";
    public static final String CAMPO_FK_USUARIO_PROPRIETARIO = "fk_usuario";
    public static final String CAMPO_FK_RESTAURANTE = "fk_restaurante";

    //Tabela Especialidades
    public static final String TABELA_ESPECIALIDADES = "tb_especialidades";
    public static final String CAMPO_ID_ESPECIALIDADES = "id";
    public static final String CAMPO_BRASILEIRA_ESP = "brasileira";
    public static final String CAMPO_MEXICANA_ESP = "mexicana";
    public static final String CAMPO_JAPONESA_ESP = "japonesa";
    public static final String CAMPO_ITALIANA_ESP = "italiana";
    public static final String CAMPO_FRANCESA_ESP = "francesa";


    //Tabela Preferências
    public static final String TABELA_PREFERENCIAS = "tb_preferencias";
    public static final String CAMPO_ID_PREFERENCIAS = "id";
    public static final String CAMPO_BRASILEIRA_PREF = "brasileira";
    public static final String CAMPO_MEXICANA_PREF = "mexicana";
    public static final String CAMPO_JAPONESA_PREF = "japonesa";
    public static final String CAMPO_ITALIANA_PREF = "italiana";
    public static final String CAMPO_FRANCESA_PREF = "francesa";



    private static final String[] TABELAS = {
            TABELA_CLIENTE, TABELA_PESSOA, TABELA_USUARIO, TABELA_ENDERECO, TABELA_CONTATO, TABELA_RESTAURANTE, TABELA_PROPRIETARIO, TABELA_ESPECIALIDADES, TABELA_PREFERENCIAS
    };

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTabelaCliente(db);
        createTabelaPessoa(db);
        createTabelaUsuario(db);
        createTabelaEndereco(db);
        createTabelaContato(db);
        createTabelaRestaurante(db);
        createTabelaProprietario(db);
        createTabelaEspecialidades(db);
        createTabelaPreferencias(db);
    }

    public void createTabelaCliente(SQLiteDatabase db){
        String sqlTbCliente =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT " +
                        ");";
        sqlTbCliente = String.format(sqlTbCliente,
                TABELA_CLIENTE, CAMPO_ID_CLIENTE, CAMPO_FK_USUARIO_CLIENTE, CAMPO_FK_PREFERENCIAS);

        db.execSQL(sqlTbCliente);

    }

    public void createTabelaPessoa(SQLiteDatabase db){
        String sqlTbPessoa =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT NOT NULL, " +
                        "  %5$s TEXT NOT NULL, " +
                        "  %6$s TEXT NOT NULL, " +
                        "  %7$s TEXT NOT NULL " +
                        ");";
        sqlTbPessoa = String.format(sqlTbPessoa,
                TABELA_PESSOA, CAMPO_ID_PESSOA, CAMPO_NOME, CAMPO_CPF, CAMPO_DATA_NASCIMENTO, CAMPO_FK_ENDERECO, CAMPO_FK_CONTATO);

        db.execSQL(sqlTbPessoa);

    }

    public void createTabelaUsuario(SQLiteDatabase db){
        String sqlTbUsuario =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT NOT NULL UNIQUE, " +
                        "  %5$s TEXT NOT NULL " +
                        ");";
        sqlTbUsuario = String.format(sqlTbUsuario,
                TABELA_USUARIO, CAMPO_ID_USUARIO, CAMPO_FK_PESSOA, CAMPO_LOGIN, CAMPO_SENHA);
        db.execSQL(sqlTbUsuario);
    }

    public void createTabelaEndereco(SQLiteDatabase db){
        String sqlTbEndereco =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT NOT NULL, " +
                        "  %5$s TEXT NOT NULL, " +
                        "  %6$s TEXT NOT NULL, " +
                        "  %7$s TEXT NOT NULL " +
                        ");";
        sqlTbEndereco = String.format(sqlTbEndereco,
                TABELA_ENDERECO, CAMPO_ID_ENDERECO, CAMPO_CEP, CAMPO_RUA, CAMPO_BAIRRO, CAMPO_CIDADE, CAMPO_NUMERO);
        db.execSQL(sqlTbEndereco);
    }

    public void createTabelaContato(SQLiteDatabase db){
        String sqlTbContato =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT NOT NULL, " +
                        "  %5$s TEXT NOT NULL " +
                        ");";
        sqlTbContato = String.format(sqlTbContato,
                TABELA_CONTATO, CAMPO_ID_CONTATO, CAMPO_TELEFONE, CAMPO_EMAIL, CAMPO_SITE);
        db.execSQL(sqlTbContato);
    }

    public void createTabelaRestaurante(SQLiteDatabase db){
        String sqlTbRestaurante =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT NOT NULL, " +
                        "  %5$s TEXT NOT NULL, " +
                        "  %6$s TEXT NOT NULL, " +
                        "  %7$s TEXT NOT NULL, " +
                        "  %8$s TEXT NOT NULL " +
                        ");";
        sqlTbRestaurante = String.format(sqlTbRestaurante,
                TABELA_RESTAURANTE, CAMPO_ID_RESTAURANTE, CAMPO_NOME_RESTAURANTE, CAMPO_CNPJ, CAMPO_NOTA_MEDIA, CAMPO_FK_ENDERECO_RESTAURANTE, CAMPO_FK_CONTATO_RESTAURANTE, CAMPO_FK_ESPECIALIDADES);

        db.execSQL(sqlTbRestaurante);
    }

    public void createTabelaProprietario(SQLiteDatabase db){
        String sqlTbProprietario =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT " +
                        ");";
        sqlTbProprietario = String.format(sqlTbProprietario,
                TABELA_PROPRIETARIO, CAMPO_ID_PROPRIETARIO, CAMPO_FK_USUARIO_PROPRIETARIO, CAMPO_FK_RESTAURANTE);
        db.execSQL(sqlTbProprietario);
    }

    public void createTabelaEspecialidades(SQLiteDatabase db){
        String sqlTbEspecialidades =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s INTEGER NOT NULL, " +
                        "  %4$s INTEGER NOT NULL, " +
                        "  %5$s INTEGER NOT NULL, " +
                        "  %6$s INTEGER NOT NULL, " +
                        "  %7$s INTEGER NOT NULL " +
                        ");";
        sqlTbEspecialidades = String.format(sqlTbEspecialidades,
                TABELA_ESPECIALIDADES, CAMPO_ID_ESPECIALIDADES, CAMPO_BRASILEIRA_ESP, CAMPO_MEXICANA_ESP, CAMPO_JAPONESA_ESP, CAMPO_ITALIANA_ESP, CAMPO_FRANCESA_ESP);
        db.execSQL(sqlTbEspecialidades);
    }

    public void createTabelaPreferencias(SQLiteDatabase db){
        String sqlTbPreferencias =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s INTEGER NOT NULL, " +
                        "  %4$s INTEGER NOT NULL, " +
                        "  %5$s INTEGER NOT NULL, " +
                        "  %6$s INTEGER NOT NULL, " +
                        "  %7$s INTEGER NOT NULL " +
                        ");";
        sqlTbPreferencias = String.format(sqlTbPreferencias,
                TABELA_PREFERENCIAS, CAMPO_ID_PREFERENCIAS, CAMPO_BRASILEIRA_PREF, CAMPO_MEXICANA_PREF, CAMPO_JAPONESA_PREF, CAMPO_ITALIANA_PREF, CAMPO_FRANCESA_PREF);
        db.execSQL(sqlTbPreferencias);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTables(db);
        onCreate(db);
    }

    private void dropTables(SQLiteDatabase db){
        StringBuilder dropTables = new StringBuilder();
        for (String tabela: TABELAS){
            dropTables.append(" DROP TABLE IF EXISTS ");
            dropTables.append(tabela);
            dropTables.append("; ");
        }
        db.execSQL(dropTables.toString());
    }

}
