package br.com.phelto.readme.usuarios.application.controllers.request;

import lombok.Data;

import java.util.List;

@Data
public class NovaPostagemRequest {

    private String idPostagem;
    private List<String> livros;
    private List<String> usuarios;
}
