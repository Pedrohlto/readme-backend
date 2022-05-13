package br.com.phelto.readme.postagens.infrastructure.integration.livros;

import br.com.phelto.readme.postagens.application.dtos.LivroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Consulta-Livros", url = "${livros.url}")
public interface ConsultaLivrosClient {

    @GetMapping("/{id_livro}")
    LivroDTO buscarLivros(@PathVariable("id_livro") String idLivro);

}
