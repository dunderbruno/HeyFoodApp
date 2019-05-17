package com.heyfood.heyfoodapp.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String NOME_DB = "heyfood.db";
    public static final int VERSAO = 1;
    public static final String TAG = "SQLite";

    //Tabela Pessoa
    public static final String TABELA_PESSOA = "tb_pessoa";
    public static final String CAMPO_ID_PESSOA = "id";
    public static final String CAMPO_NOME = "nome";
    public static final String CAMPO_CPF = "cpf";
    public static final String CAMPO_DATA_NASCIMENTO = "data_nascimento";

    //Tabela Usuario
    public static final String TABELA_USUARIO = "tb_usuario";
    public static final String CAMPO_ID_USUARIO = "id";
    public static final String CAMPO_FK_PESSOA = "fk_pessoa";
    public static final String CAMPO_LOGIN = "login";
    public static final String CAMPO_SENHA = "senha";

    private static final String[] TABELAS = {
            TABELA_PESSOA, TABELA_USUARIO
    };

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTabelaPessoa(db);
        createTabelaUsuario(db);
    }

    public void createTabelaPessoa(SQLiteDatabase db){
        String sqlTbPessoa =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL, " +
                        "  %4$s TEXT NOT NULL UNIQUE, " +
                        "  %5$s TEXT NOT NULL " +
                        ");";
        sqlTbPessoa = String.format(sqlTbPessoa,
                TABELA_PESSOA, CAMPO_ID_PESSOA, CAMPO_NOME, CAMPO_CPF, CAMPO_DATA_NASCIMENTO);

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

