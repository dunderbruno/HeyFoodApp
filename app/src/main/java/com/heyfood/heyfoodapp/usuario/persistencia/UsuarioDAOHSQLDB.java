package com.heyfood.heyfoodapp.usuario.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractHSQLDBDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

public class UsuarioDAOHSQLDB extends AbstractHSQLDBDAO {
    public Usuario getUsuario(String email) {
        Usuario result = null;
        SQLiteDatabase db = super.getReadableDatabase();
        String sql = "SELECT * FROM " +DBHelper.TABELA_USUARIO+ " U WHERE U." + DBHelper.CAMPO_LOGIN + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if (cursor.moveToFirst()) {
            result = createUsuario(cursor);
        }
        super.close(cursor,db);
        return result;
    }

    public Usuario getUsuario(String email, String password) {
        Usuario result = getUsuario(email);
        if (result != null && !password.equals(result.getSenha())) {
            result = null;
        }
        return result;
    }

    public void cadastrar(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_LOGIN, usuario.getLogin());
        values.put(DBHelper.CAMPO_PASSWORD, usuario.getSenha());
        db.insert(DBHelper.TABELA_USUARIO, null, values);
        super.close(db);
    }

    private Usuario createUsuario(Cursor cursor) {
        Usuario result = new Usuario();
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID);
        result.setId(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_LOGIN);
        result.setLogin(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_PASSWORD);
        result.setSenha(cursor.getString(columnIndex));
        return result;
    }
}
