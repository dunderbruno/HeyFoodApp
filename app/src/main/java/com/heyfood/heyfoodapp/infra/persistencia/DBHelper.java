package com.heyfood.heyfoodapp.infra.persistencia;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.infra.ui.HeyFoodApp;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOME_DB = "mpooapp.db";
    private static final int VERSAO = 1;

    public static final String CAMPO_ID = "ID";
    // TABELA DOS USUARIOS
    public static final String TABELA_USUARIO = "USUARIO";
    public static final String CAMPO_LOGIN = "LOGIN";
    public static final String CAMPO_PASSWORD = "PASSWORD";

    private static final String[] TABELAS = {
            TABELA_USUARIO
    };

    DBHelper(){
        super(HeyFoodApp.getContext(), NOME_DB,null, VERSAO);
    }

}
