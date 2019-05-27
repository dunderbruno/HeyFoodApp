package com.heyfood.heyfoodapp.proprietario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.heyfood.heyfoodapp.usuario.persistencia.UsuarioDAO;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;
import com.heyfood.heyfoodapp.proprietario.dominio.Proprietario;

public class ProprietarioDAO extends AbstractDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public ProprietarioDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Proprietario getProprietario(int id) {
        Proprietario result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_PROPRIETARIO + " WHERE " + DBHelper.CAMPO_ID_PROPRIETARIO + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()) {
            result = createProprietario(cursor);
        }
        super.close(cursor, db);
        return result;
    }

    public int cadastrar(Proprietario proprietario) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_FK_USUARIO_PROPRIETARIO, proprietario.getUsuario().getId());
        values.put(DBHelper.CAMPO_FK_RESTAURANTE, proprietario.getRestaurante().getId());

        long retorno = db.insert(DBHelper.TABELA_PESSOA, null, values);
        super.close(db);

        return (int) retorno;
    }

    private Proprietario createProprietario(Cursor cursor){
        Proprietario result = new Proprietario();
        UsuarioDAO usuarioDAO = new UsuarioDAO((context));
        RestauranteDAO restauranteDAO = new RestauranteDAO(context);
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_PROPRIETARIO);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_USUARIO_PROPRIETARIO);
        result.setUsuario(usuarioDAO.getUsuario(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_RESTAURANTE);
        result.setRestaurante(restauranteDAO.getRestaurante(cursor.getInt(columnIndex)));
        return result;
    }
}
