package br.com.phelto.readme.usuarios.domain.repository;

import br.com.phelto.readme.usuarios.application.controllers.request.NovaPostagemRequest;
import br.com.phelto.readme.usuarios.domain.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    Usuario incluir(Usuario usuario);
    void seguirUsuario(String idUsuario, String novoSeguidor);
    void seguirLivro(String idUsuario, String idLivro);
    List<Usuario> listarSeguidores(String idUsuario);
    List<String> listarLivrosSeguindo(String idUsuario);
    void inserirPostagem(NovaPostagemRequest novaPostagemRequest);
    Usuario detalharUsuario(String idUsuario);
    List<Usuario> listarUsuarios();
    List<Usuario> listarSeguindo(String idUsuario);
}
