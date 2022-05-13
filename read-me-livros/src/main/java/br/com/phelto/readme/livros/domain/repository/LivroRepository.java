package br.com.phelto.readme.livros.domain.repository;

import br.com.phelto.readme.livros.domain.entities.Livro;

import java.util.List;

public interface LivroRepository {

    Livro buscarPorId(String idLivro);

    List<Livro> buscarLivrosPorNome(String nomeLivro);

    List<Livro> listarLivros();

    void novoSeguidor(String idLivro, String idUsuario);

    List<String> listarSeguidores(String idLivro);
}
