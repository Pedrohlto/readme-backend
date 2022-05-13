package br.com.phelto.readme.postagens.application.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class PostagemCriadaEvent extends ApplicationEvent {

    private String idPostagem;
    private List<String> livros;
    private List<String> usuarios;

    public PostagemCriadaEvent(Object source, String idPostagem, List<String> livros, List<String> usuarios) {
        super(source);
        this.idPostagem = idPostagem;
        this.livros = livros;
        this.usuarios = usuarios;
    }

}
