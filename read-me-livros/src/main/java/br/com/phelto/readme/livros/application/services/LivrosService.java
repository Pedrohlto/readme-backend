package br.com.phelto.readme.livros.application.services;

import br.com.phelto.readme.livros.application.controllers.request.NovoSeguidorRequest;
import br.com.phelto.readme.livros.application.controllers.responses.LivroResponse;
import br.com.phelto.readme.livros.domain.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LivrosService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroResponse> listarLivros(String nome){

        if(Objects.isNull(nome)) return livroRepository.listarLivros()
                .stream()
                .map(LivroResponse::new)
                .collect(Collectors.toList());

        return livroRepository.buscarLivrosPorNome(nome)
                .stream()
                .map(LivroResponse::new)
                .collect(Collectors.toList());
    }

    public LivroResponse buscarLivroPorId(String idLivro) {
        var livro = livroRepository.buscarPorId(idLivro);
        return new LivroResponse(livro);
    }

    public void seguirLivro(String idLivro, NovoSeguidorRequest novoSeguidor) {
        livroRepository.novoSeguidor(idLivro, novoSeguidor.getIdUsuario());
    }

    public List<String> listarSeguidores(String idLivro) {
        return livroRepository.listarSeguidores(idLivro);
    }
}
