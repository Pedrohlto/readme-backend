package br.com.phelto.readme.postagens.application.events.listeners;

import br.com.phelto.readme.postagens.application.events.PostagemCriadaEvent;
import br.com.phelto.readme.postagens.infrastructure.integration.livros.ConsultaLivrosAdapter;
import br.com.phelto.readme.postagens.infrastructure.integration.livros.outbound.NovaAvaliacaoLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnviaAvaliacaoLivro implements ApplicationListener<PostagemCriadaEvent> {

    @Autowired
    private ConsultaLivrosAdapter consultaLivrosAdapter;

    @Override
    public void onApplicationEvent(PostagemCriadaEvent event) {
        var idLivro = event.getLivros().stream().findFirst().get();
        if(Objects.nonNull(event.getNota()))
        consultaLivrosAdapter.enviaAvaliacaoLivro(idLivro, new NovaAvaliacaoLivro(event.getNota()));
    }
}
