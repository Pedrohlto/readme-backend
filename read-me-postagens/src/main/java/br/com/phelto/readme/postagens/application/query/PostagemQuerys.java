package br.com.phelto.readme.postagens.application.query;

import br.com.phelto.readme.postagens.application.dtos.PagePostagem;
import br.com.phelto.readme.postagens.application.dtos.PostagemDTO;

public interface PostagemQuerys {

    PagePostagem listarPostagem(Integer pagina, Integer tamanho, String idUsuario);
    PostagemDTO getPostagemById(String idPostagem);
    PagePostagem listarPostagemPorLivro(Integer pagina, Integer tamanho, String idLivro);
}
