package com.heyfood.heyfoodapp.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;
import com.heyfood.heyfoodapp.pessoa.persistencia.PessoaDAO;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

public class UsuarioDAO extends AbstractDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public UsuarioDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Usuario getUsuarioById(int id){
        Usuario result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_USUARIO+ " WHERE " + DBHelper.CAMPO_ID_USUARIO + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()) {
            result = createUsuario(cursor);
        }
        super.close(cursor, db);

        return result;
    }

    public Usuario getUsuario(String login) {
        Usuario result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " +DBHelper.TABELA_USUARIO+ " WHERE " + DBHelper.CAMPO_LOGIN + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{login});
        if (cursor.moveToFirst()) {
            result = createUsuario(cursor);
        }
        super.close(cursor,db);
        return result;
    }

    public Usuario getUsuario(String login, String senha) {
        Usuario result = getUsuario(login);
        if (result != null && !senha.equals(result.getSenha())) {
            result = null;
        }
        return result;
    }

    public int cadastrar(Usuario usuario) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_FK_PESSOA, usuario.getPessoa().getId());
        values.put(DBHelper.CAMPO_LOGIN, usuario.getLogin());
        values.put(DBHelper.CAMPO_SENHA, usuario.getSenha());

        long retorno = db.insert(DBHelper.TABELA_USUARIO, null, values);
        super.close(db);

        return (int) retorno;
    }

    private Usuario createUsuario(Cursor cursor) {
        Usuario result = new Usuario();
        PessoaDAO pessoaDAO = new PessoaDAO(context);
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_USUARIO);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_PESSOA);
        result.setPessoa(pessoaDAO.getPessoa(cursor.getInt(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_LOGIN);
        result.setLogin(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_SENHA);
        result.setSenha(cursor.getString(columnIndex));
        return result;
    }

}
