package com.heyfood.heyfoodapp.proprietario.negocio;

import android.content.Context;

import com.heyfood.heyfoodapp.contato.persistencia.ContatoDAO;
import com.heyfood.heyfoodapp.endereco.persistencia.EnderecoDAO;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.pessoa.persistencia.PessoaDAO;
import com.heyfood.heyfoodapp.proprietario.dominio.Proprietario;
import com.heyfood.heyfoodapp.proprietario.persistencia.ProprietarioDAO;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;
import com.heyfood.heyfoodapp.usuario.persistencia.UsuarioDAO;

public class ProprietarioServices {
    private ProprietarioDAO proprietarioDAO;
    private UsuarioDAO usuarioDAO;
    private PessoaDAO pessoaDAO;
    //private ContatoDAO contatoDAO;
    //private EnderecoDAO enderecoDAO;

    public ProprietarioServices(Context context){
        proprietarioDAO = new ProprietarioDAO(context);
        usuarioDAO = new UsuarioDAO(context);
        pessoaDAO = new PessoaDAO(context);
    }

    public void cadastrar(Proprietario proprietario) throws Exception {
        if (usuarioDAO.getUsuario(proprietario.getUsuario().getLogin()) != null){
            throw new Exception();
        }
        int idPessoa = pessoaDAO.cadastrar(proprietario.getUsuario().getPessoa());
        proprietario.getUsuario().getPessoa().setId(idPessoa);

        int idUsuario = usuarioDAO.cadastrar(proprietario.getUsuario());
        proprietario.getUsuario().setId(idUsuario);

        int idProprietario = proprietarioDAO.cadastrar(proprietario);
        proprietario.setId(idProprietario);
    }

    public void login(String email, String password) throws Exception {
        Usuario usuario = usuarioDAO.getUsuario(email, password);
        if (usuario == null) {
            throw new Exception();
        }
        Proprietario proprietario = proprietarioDAO.getProprietario(usuario.getId());
        Sessao.instance.setProprietario(proprietario);
    }

    public void logout() {
        Sessao sessao = Sessao.instance;
        sessao.reset();
    }
}
