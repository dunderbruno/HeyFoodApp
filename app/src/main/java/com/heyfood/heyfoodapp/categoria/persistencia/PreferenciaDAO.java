package com.heyfood.heyfoodapp.categoria.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.categoria.dominio.Categoria;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;

public class PreferenciaDAO extends AbstractDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public PreferenciaDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Categoria getPreferencia(int id){
        Categoria result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_PREFERENCIAS+ " WHERE " + DBHelper.CAMPO_ID_PREFERENCIAS + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()) {
            result = createPreferencia(cursor);
        }
        super.close(cursor, db);
        return result;
    }

    public int cadastrar(Categoria categoria){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.CAMPO_ACAI_PREF, categoria.getAcai());
        values.put(DBHelper.CAMPO_BRASILEIRA_PREF, categoria.getBrasileira());
        values.put(DBHelper.CAMPO_CARNES_PREF, categoria.getCarnes());
        values.put(DBHelper.CAMPO_CONTEMPORANEA_PREF, categoria.getContemporanea());
        values.put(DBHelper.CAMPO_ITALIANA_PREF, categoria.getItaliana());
        values.put(DBHelper.CAMPO_JAPONESA_PREF, categoria.getJaponesa());
        values.put(DBHelper.CAMPO_LANCHES_PREF, categoria.getLanches());
        values.put(DBHelper.CAMPO_MARMITA_PREF, categoria.getMarmita());
        values.put(DBHelper.CAMPO_PIZZA_PREF, categoria.getPizza());
        values.put(DBHelper.CAMPO_SAUDAVEL_PREF, categoria.getSaudavel());
        values.put(DBHelper.CAMPO_ALACARTE_PREF, categoria.getAlacarte());
        values.put(DBHelper.CAMPO_RODIZIO_PREF, categoria.getRodizio());
        values.put(DBHelper.CAMPO_DELIVERY_PREF, categoria.getDelivery());
        values.put(DBHelper.CAMPO_SELFSERVICE_PREF, categoria.getSelfservice());

        long retorno = db.insert(DBHelper.TABELA_PREFERENCIAS, null, values);
        super.close(db);
        return (int) retorno;
    }

    private Boolean convert(int valor){
        if (valor == 1){
            return true;
        }else{
            return false;
        }
    }

    private Categoria createPreferencia(Cursor cursor){
        Categoria result = new Categoria();
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_PREFERENCIAS);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ACAI_PREF);
        result.setAcai(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_BRASILEIRA_PREF);
        result.setBrasileira(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_CARNES_PREF);
        result.setCarnes(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_CONTEMPORANEA_PREF);
        result.setContemporanea(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ITALIANA_PREF);
        result.setItaliana(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_JAPONESA_PREF);
        result.setJaponesa(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_LANCHES_PREF);
        result.setLanches(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_MARMITA_PREF);
        result.setMarmita(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_PIZZA_PREF);
        result.setSaudavel(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_SAUDAVEL_PREF);
        result.setSaudavel(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ALACARTE_PREF);
        result.setAlacarte(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_RODIZIO_PREF);
        result.setRodizio(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_DELIVERY_PREF);
        result.setDelivery(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_SELFSERVICE_PREF);
        result.setSelfservice(convert(columnIndex));
        return result;
    }
}
