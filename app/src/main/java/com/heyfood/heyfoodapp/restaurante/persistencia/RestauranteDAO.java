package com.heyfood.heyfoodapp.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.contato.persistencia.ContatoDAO;
import com.heyfood.heyfoodapp.endereco.persistencia.EnderecoDAO;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;
import com.heyfood.heyfoodapp.pessoa.dominio.Pessoa;
import com.heyfood.heyfoodapp.proprietario.persistencia.ProprietarioDAO;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;

public class RestauranteDAO extends AbstractDAO{
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public RestauranteDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Restaurante getRestaurante(int id) {
        Restaurante result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_RESTAURANTE + " WHERE " + DBHelper.CAMPO_ID_RESTAURANTE + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()) {
            result = createRestaurante(cursor);
        }
        super.close(cursor, db);
        return result;
    }

    public int cadastrar(Restaurante restaurante) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_NOME_RESTAURANTE, restaurante.getNome());
        values.put(DBHelper.CAMPO_CNPJ, restaurante.getCnpj());
        //values.put(DBHelper.CAMPO_NOTA_MEDIA, restaurante.getNotaMedia());
        values.put(DBHelper.CAMPO_FK_ENDERECO_RESTAURANTE, restaurante.getEndereco().getId());
        values.put(DBHelper.CAMPO_FK_CONTATO_RESTAURANTE, restaurante.getContato().getId());

        long retorno = db.insert(DBHelper.TABELA_RESTAURANTE, null, values);

        ProprietarioDAO proprietarioDAO = new ProprietarioDAO(context);
        proprietarioDAO.setRestaurante(retorno);

        super.close(db);

        return (int) retorno;
    }

    private Restaurante createRestaurante(Cursor cursor){
        Restaurante result = new Restaurante();
        EnderecoDAO enderecoDAO = new EnderecoDAO(context);
        ContatoDAO contatoDAO = new ContatoDAO(context);
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_RESTAURANTE);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_NOME_RESTAURANTE);
        result.setNome(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_CNPJ);
        result.setCnpj(cursor.getString(columnIndex));
        //columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_NOTA_MEDIA);
        //result.setNotaMedia(cursor.getFloat(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_ENDERECO_RESTAURANTE);
        result.setEndereco(enderecoDAO.getEndereco(cursor.getInt(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_CONTATO_RESTAURANTE);
        result.setContato(contatoDAO.getContato(cursor.getInt(columnIndex)));
        return result;
    }
}
