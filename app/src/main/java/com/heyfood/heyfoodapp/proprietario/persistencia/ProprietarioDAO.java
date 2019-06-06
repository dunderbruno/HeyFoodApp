package com.heyfood.heyfoodapp.proprietario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.usuario.persistencia.UsuarioDAO;
import com.heyfood.heyfoodapp.proprietario.dominio.Proprietario;
import com.heyfood.heyfoodapp.restaurante.persistencia.RestauranteDAO;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;

public class ProprietarioDAO extends AbstractDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public ProprietarioDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Proprietario getProprietario(int fk_usuario) {
        Proprietario result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_PROPRIETARIO + " WHERE " + DBHelper.CAMPO_FK_USUARIO_PROPRIETARIO + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(fk_usuario)});
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
        //values.put(DBHelper.CAMPO_FK_RESTAURANTE, proprietario.getRestaurante().getId());

        long retorno = db.insert(DBHelper.TABELA_PROPRIETARIO, null, values);
        super.close(db);

        return (int) retorno;
    }

    public void setRestaurante(long idRestaurante){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_FK_RESTAURANTE, idRestaurante);

        //String idProprietario = Integer.toString(Sessao.instance.getProprietario().getId());
        String[] idProprietario = new String[]{Integer.toString(Sessao.instance.getProprietario().getId())};

        //String sql = "SELECT * FROM " + DBHelper.TABELA_PROPRIETARIO + " WHERE " + DBHelper.CAMPO_FK_USUARIO_PROPRIETARIO + " LIKE ?;";
        //Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(fk_usuario)});

        db.update(DBHelper.TABELA_PROPRIETARIO, values, DBHelper.CAMPO_ID_PROPRIETARIO+"=?", idProprietario);
        super.close();
    }

    private Proprietario createProprietario(Cursor cursor){
        Proprietario result = new Proprietario();
        UsuarioDAO usuarioDAO = new UsuarioDAO((context));
        //RestauranteDAO restauranteDAO = new RestauranteDAO(context);
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_PROPRIETARIO);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_USUARIO_PROPRIETARIO);
        result.setUsuario(usuarioDAO.getUsuarioById(cursor.getInt(columnIndex)));
        //columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_RESTAURANTE);
        //result.setRestaurante(restauranteDAO.getRestaurante(cursor.getInt(columnIndex)));
        return result;
    }
}
