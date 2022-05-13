package br.com.phelto.readme.postagens.infrastructure.integration.livros;

import br.com.phelto.readme.postagens.application.dtos.LivroDTO;
import br.com.phelto.readme.postagens.application.ports.ConsultaLivros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsultaLivrosAdapter implements ConsultaLivros {

    @Autowired
    private ConsultaLivrosClient livrosClient;

    @Override
    public List<LivroDTO> consultaLivros(List<String> ids) {
        List<LivroDTO> livros = new ArrayList<>();

        ids.forEach( id -> {
            livros.add(livrosClient.buscarLivros(id));
        });

        return  livros;
    }
}
