package br.com.phelto.readme.postagens.application.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class LivroDTO implements Serializable {
    private String id;
    private String isbn;
    private String titulo;
    private String autor;
    private String fotoUrl;
}
