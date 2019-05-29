package com.heyfood.heyfoodapp.cliente.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.contato.persistencia.ContatoDAO;
import com.heyfood.heyfoodapp.endereco.persistencia.EnderecoDAO;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;
import com.heyfood.heyfoodapp.pessoa.dominio.Pessoa;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;
import com.heyfood.heyfoodapp.usuario.persistencia.UsuarioDAO;

/**
 * Created by GABRIEL.CABOCLO on 29/05/2019.
 */

public class ClienteDAO extends AbstractDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public ClienteDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Cliente getCliente(int fk_usuario) {
        Cliente result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_CLIENTE+ " WHERE " + DBHelper.CAMPO_FK_USUARIO_CLIENTE + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(fk_usuario)});
        if (cursor.moveToFirst()) {
            result = createCliente(cursor);
        }
        super.close(cursor, db);
        return result;
    }

    public int cadastrar(Cliente cliente) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_FK_USUARIO_CLIENTE, cliente.getUsuario().getId());

        long retorno = db.insert(DBHelper.TABELA_CLIENTE, null, values);
        super.close(db);

        return (int) retorno;
    }

    private Cliente createCliente(Cursor cursor){
        Cliente result = new Cliente();
        UsuarioDAO usuarioDAO = new UsuarioDAO(context);
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_CLIENTE);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_USUARIO_CLIENTE);
        result.setUsuario(usuarioDAO.getUsuarioById(cursor.getInt(columnIndex)));

        return result;
    }

}
