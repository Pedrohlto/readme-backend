package br.com.phelto.readme.postagens.application.events.listeners;

import br.com.phelto.readme.postagens.application.events.PostagemCriadaEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EnviaInformacoesParaUsuarios implements ApplicationListener<PostagemCriadaEvent> {

    @Override
    public void onApplicationEvent(PostagemCriadaEvent event) {
        System.out.println("Postagem foi criada e estou testando");
    }
}
