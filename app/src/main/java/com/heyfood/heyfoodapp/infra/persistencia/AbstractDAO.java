package com.heyfood.heyfoodapp.infra.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.infra.HeyFoodAppRuntimeException;

import java.io.Closeable;
import java.io.IOException;

public abstract class AbstractDAO {
    protected void close(Closeable... args) throws HeyFoodAppRuntimeException {
        for (Closeable closable : args) {
            try {
                closable.close();
            } catch (IOException e) {
                throw new HeyFoodAppRuntimeException("Erro ao fechar as conexões",e);
            }
        }
    }

    /*
    protected SQLiteDatabase getReadableDatabase() {
        DBHelper dbHelper = new DBHelper();
        return dbHelper.getReadableDatabase();
    }

    protected SQLiteDatabase getWritableDatabase() {
        DBHelper dbHelper = new DBHelper();
        return dbHelper.getWritableDatabase();
    }
    */

}

