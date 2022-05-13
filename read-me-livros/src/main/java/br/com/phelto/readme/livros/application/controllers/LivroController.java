package br.com.phelto.readme.livros.application.controllers;

import br.com.phelto.readme.livros.application.controllers.request.NovoSeguidorRequest;
import br.com.phelto.readme.livros.application.controllers.responses.LivroResponse;
import br.com.phelto.readme.livros.application.services.LivrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/livros")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LivroController {
    private final LivrosService livrosService;

    @GetMapping
    public List<LivroResponse> listarLivros(@RequestParam(value = "nomeLivro", required = false) String nomeLivro){
        return livrosService.listarLivros(nomeLivro);
    }

    @GetMapping("/{id_livro}")
    public LivroResponse detalharLivro(@PathVariable("id_livro") String idLivro){
        return livrosService.buscarLivroPorId(idLivro);
    }

    @PostMapping("/{id_livro}/seguidores")
    public void criarNovoSeguidor(@PathVariable("id_livro") String idLivro
                                ,@RequestBody NovoSeguidorRequest novoSeguidor){
        livrosService.seguirLivro(idLivro, novoSeguidor);
    }

    @GetMapping("/{id_livro}/seguidores")
    public List<String> listarSeguidores(@PathVariable("id_livro") String idLivro){
        return livrosService.listarSeguidores(idLivro);
    }
}
