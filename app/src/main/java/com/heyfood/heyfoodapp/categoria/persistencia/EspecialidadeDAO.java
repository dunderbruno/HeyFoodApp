package com.heyfood.heyfoodapp.categoria.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.categoria.dominio.Categoria;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;

public class EspecialidadeDAO extends AbstractDAO{
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public EspecialidadeDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Categoria getEspecialidade(int id){
        Categoria result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_ESPECIALIDADES+ " WHERE " + DBHelper.CAMPO_ID_ESPECIALIDADES + " LIKE ?;";
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
        values.put(DBHelper.CAMPO_ACAI_ESP, categoria.getAcai());
        values.put(DBHelper.CAMPO_BRASILEIRA_ESP, categoria.getBrasileira());
        values.put(DBHelper.CAMPO_CARNES_ESP, categoria.getCarnes());
        values.put(DBHelper.CAMPO_CONTEMPORANEA_ESP, categoria.getContemporanea());
        values.put(DBHelper.CAMPO_ITALIANA_ESP, categoria.getItaliana());
        values.put(DBHelper.CAMPO_JAPONESA_ESP, categoria.getJaponesa());
        values.put(DBHelper.CAMPO_LANCHES_ESP, categoria.getLanches());
        values.put(DBHelper.CAMPO_MARMITA_ESP, categoria.getMarmita());
        values.put(DBHelper.CAMPO_PIZZA_ESP, categoria.getPizza());
        values.put(DBHelper.CAMPO_SAUDAVEL_ESP, categoria.getSaudavel());
        values.put(DBHelper.CAMPO_ALACARTE_ESP, categoria.getAlacarte());
        values.put(DBHelper.CAMPO_RODIZIO_ESP, categoria.getRodizio());
        values.put(DBHelper.CAMPO_DELIVERY_ESP, categoria.getDelivery());
        values.put(DBHelper.CAMPO_SELFSERVICE_ESP, categoria.getSelfservice());

        long retorno = db.insert(DBHelper.TABELA_ESPECIALIDADES, null, values);
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
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_ESPECIALIDADES);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ACAI_ESP);
        result.setAcai(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_BRASILEIRA_ESP);
        result.setBrasileira(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_CARNES_ESP);
        result.setCarnes(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_CONTEMPORANEA_ESP);
        result.setContemporanea(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ITALIANA_ESP);
        result.setItaliana(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_JAPONESA_ESP);
        result.setJaponesa(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_LANCHES_ESP);
        result.setLanches(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_MARMITA_ESP);
        result.setMarmita(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_PIZZA_ESP);
        result.setSaudavel(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_SAUDAVEL_ESP);
        result.setSaudavel(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ALACARTE_ESP);
        result.setAlacarte(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_RODIZIO_ESP);
        result.setRodizio(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_DELIVERY_ESP);
        result.setDelivery(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_SELFSERVICE_ESP);
        result.setSelfservice(convert(columnIndex));
        return result;
    }
}
