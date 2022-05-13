package br.com.phelto.readme.postagens.domain.repository;

import br.com.phelto.readme.postagens.application.dtos.PostagemDTO;
import br.com.phelto.readme.postagens.domain.entities.Postagem;

public interface PostagemRepository {
    PostagemDTO save(Postagem postagem);
}
