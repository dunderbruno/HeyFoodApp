package com.heyfood.heyfoodapp.usuario.negocio;

import android.content.Context;

import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.pessoa.persistencia.PessoaDAO;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;
import com.heyfood.heyfoodapp.usuario.persistencia.UsuarioDAO;

public class UsuarioServices {
    private UsuarioDAO usuarioDAO;
    private PessoaDAO pessoaDAO;

    public UsuarioServices(Context context){
        usuarioDAO = new UsuarioDAO(context);
        pessoaDAO = new PessoaDAO(context);

    }

    public void cadastrar(Usuario usuario) throws Exception {
        if (usuarioDAO.getUsuario(usuario.getLogin()) != null) {
            throw new Exception();
        }
        int idPessoa = pessoaDAO.cadastrar(usuario.getPessoa());
        usuario.getPessoa().setId(idPessoa);
        int idUsuario = usuarioDAO.cadastrar(usuario);
        usuario.setId(idUsuario);
    }

    public void login(String email, String password) throws Exception {
        Usuario usuario = usuarioDAO.getUsuario(email, password);
        if (usuario == null) {
            throw new Exception();
        }
        Sessao.instance.setUsuario(usuario);
    }

    public void logout() {
        Sessao sessao = Sessao.instance;
        sessao.reset();
    }
}
