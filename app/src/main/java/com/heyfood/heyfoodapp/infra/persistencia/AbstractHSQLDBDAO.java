package com.heyfood.heyfoodapp.infra.persistencia;

import android.database.sqlite.SQLiteDatabase;
import com.heyfood.heyfoodapp.infra.HeyFoodAppRunTimeException;
import java.io.Closeable;
import java.io.IOException;

public abstract class AbstractHSQLDBDAO {
    protected void close(Closeable... args) throws HeyFoodAppRunTimeException {
        for (Closeable closable : args) {
            try {
                closable.close();
            } catch (IOException e) {
                throw new HeyFoodAppRunTimeException("Erro ao fechar as conexões",e);
            }
        }
    }

    protected SQLiteDatabase getReadableDatabase() {
        DBHelper dbHelper = new DBHelper();
        return dbHelper.getReadableDatabase();
    }

    protected SQLiteDatabase getWritableDatabase() {
        DBHelper dbHelper = new DBHelper();
        return dbHelper.getWritableDatabase();
    }
}
