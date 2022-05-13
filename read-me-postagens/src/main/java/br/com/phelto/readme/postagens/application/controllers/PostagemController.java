package br.com.phelto.readme.postagens.application.controllers;

import br.com.phelto.readme.postagens.application.dtos.NovaPostagemDTO;
import br.com.phelto.readme.postagens.application.dtos.PagePostagem;
import br.com.phelto.readme.postagens.application.dtos.PostagemDTO;
import br.com.phelto.readme.postagens.application.query.PostagemQuerys;
import br.com.phelto.readme.postagens.application.usecases.CriarPostagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/postagens")
public class PostagemController {

    @Autowired
    PostagemQuerys postagemQuerys;

    @Autowired
    CriarPostagem criarPostagem;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostagemDTO criarNovaPostagem(@RequestBody NovaPostagemDTO novaPostagemDTO){
        return criarPostagem.execute(novaPostagemDTO);
    }

    @GetMapping
    public PagePostagem listarPostagem(@RequestParam Integer pagina
                                      ,@RequestParam Integer tamanho
                                      ,@RequestParam(required = false) String idUsuario
                                      ,@RequestParam(required = false) String idLivro){
        if(Objects.nonNull(idLivro))
        return postagemQuerys.listarPostagemPorLivro(pagina, tamanho, idLivro);

        return postagemQuerys.listarPostagem(pagina, tamanho, idUsuario);
    }

    @GetMapping("/{idPostagem}")
    public PostagemDTO getPostagem(@PathVariable String idPostagem){
        return postagemQuerys.getPostagemById(idPostagem);
    }

    @GetMapping("/teste/{idPostagem}")
    @CacheEvict(value = "postagem"
               ,key="#idPostagem"
              ,cacheManager = "cacheManager3Horas")
    public String teste(@PathVariable String idPostagem){
        return "OK";
    }
}
