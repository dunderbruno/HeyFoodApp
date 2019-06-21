package com.heyfood.heyfoodapp.avaliacao.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.avaliacao.dominio.Avaliacao;
import com.heyfood.heyfoodapp.cliente.persistencia.ClienteDAO;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;

public class AvaliacaoDAO extends AbstractDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public AvaliacaoDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Avaliacao getAvaliacao(long id) {
        Avaliacao result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_AVALIACAO + " WHERE " + DBHelper.CAMPO_ID_AVALIACAO + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Long.toString(id)});
        if (cursor.moveToFirst()) {
            result = createAvaliacao(cursor);
        }
        super.close(cursor, db);
        return result;
    }

    public long cadastrar(Avaliacao avaliacao) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_FK_RESTAURANTE, avaliacao.getRestaurante().getId());
        values.put(DBHelper.CAMPO_FK_CLIENTE, avaliacao.getCliente().getId());
        values.put(DBHelper.CAMPO_NOTA, avaliacao.getNota());
        long id = db.insert(DBHelper.TABELA_AVALIACAO, null, values);
        super.close(db);
        return id;
    }

    private Avaliacao createAvaliacao(Cursor cursor){
        Avaliacao result = new Avaliacao();
        RestauranteDAO restauranteDAO = new RestauranteDAO(context);
        ClienteDAO clienteDAO = new ClienteDAO(context);
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_AVALIACAO);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_RESTAURANTE);
        result.setRestaurante(restauranteDAO.getRestaurante(cursor.getInt(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_CLIENTE);
        result.setCliente(clienteDAO.getCliente(cursor.getInt(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_NOTA);
        result.setNota(Integer.parseInt(cursor.getString(columnIndex)));
        return result;
    }

}
