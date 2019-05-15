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

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTabelaUsuario(db);
    }

    private void createTabelaUsuario(SQLiteDatabase db) {
        String sqlTbUsuario =
                "CREATE TABLE %1$s ( "  +
                        "  %2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "  %3$s TEXT NOT NULL UNIQUE, " +
                        "  %4$s TEXT NOT NULL " +
                        ");";
        sqlTbUsuario = String.format(sqlTbUsuario,
                TABELA_USUARIO, CAMPO_ID, CAMPO_LOGIN, CAMPO_PASSWORD);
        db.execSQL(sqlTbUsuario);
    }
    private void dropTables(SQLiteDatabase db) {
        StringBuilder dropTables = new StringBuilder();
        for (String tabela : TABELAS) {
            dropTables.append(" DROP TABLE IF EXISTS ");
            dropTables.append(tabela);
            dropTables.append("; ");
        }
        db.execSQL(dropTables.toString());
    }
}
