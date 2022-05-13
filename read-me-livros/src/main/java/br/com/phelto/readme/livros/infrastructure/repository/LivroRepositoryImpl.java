package br.com.phelto.readme.livros.infrastructure.repository;

import br.com.phelto.readme.livros.domain.entities.Livro;
import br.com.phelto.readme.livros.domain.repository.LivroRepository;
import br.com.phelto.readme.livros.infrastructure.repository.mysql.entities.LivroModel;
import br.com.phelto.readme.livros.infrastructure.repository.mysql.entities.LivroRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LivroRepositoryImpl implements LivroRepository {

    @Autowired
    private LivroRepositoryJpa livroJpa;

    @Override
    public Livro buscarPorId(String idLivro) {
        var livro = livroJpa.findById(idLivro);
        return livro.get().toDomain();
    }

    @Override
    public List<Livro> buscarLivrosPorNome(String nomeLivro) {
        var livros = livroJpa.buscarPorNome(nomeLivro);
        return livros.stream().map(LivroModel::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Livro> listarLivros() {
        var livros = livroJpa.findAll();
        return livros.stream().map(LivroModel::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void novoSeguidor(String idLivro, String idUsuario) {
        var livro = livroJpa.findById(idLivro);
        livro.get().getSeguidores().add(idUsuario);
    }

    @Override
    public List<String> listarSeguidores(String idLivro) {
        return livroJpa.findById(idLivro).get().getSeguidores();
    }
}
