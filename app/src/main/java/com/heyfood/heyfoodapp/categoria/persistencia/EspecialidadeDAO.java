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
        values.put(DBHelper.CAMPO_BRASILEIRA_ESP, categoria.getBrasileira());
        values.put(DBHelper.CAMPO_MEXICANA_ESP, categoria.getMexicana());
        values.put(DBHelper.CAMPO_JAPONESA_ESP, categoria.getJaponesa());
        values.put(DBHelper.CAMPO_ITALIANA_ESP, categoria.getItaliana());
        values.put(DBHelper.CAMPO_FRANCESA_ESP, categoria.getFrancesa());

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
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_BRASILEIRA_ESP);
        columnIndex = Integer.parseInt(cursor.getString(columnIndex));
        result.setBrasileira(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_MEXICANA_ESP);
        columnIndex = Integer.parseInt(cursor.getString(columnIndex));
        result.setMexicana(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_JAPONESA_ESP);
        columnIndex = Integer.parseInt(cursor.getString(columnIndex));
        result.setJaponesa(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ITALIANA_ESP);
        columnIndex = Integer.parseInt(cursor.getString(columnIndex));
        result.setItaliana(convert(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FRANCESA_ESP);
        columnIndex = Integer.parseInt(cursor.getString(columnIndex));
        result.setFrancesa(convert(columnIndex));
        return result;
    }
}
