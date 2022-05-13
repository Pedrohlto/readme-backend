package br.com.phelto.readme.livros.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class Livro {

    private String id;
    private String isbn;
    private String titulo;
    private Autor autor;
    private String edicao;
    private Integer ano;
    private Editora editora;
    private Integer paginas;
    private String fotoUrl;
    private String sinopse;
    private LocalDate dataPublicacao;
    private String Idioma;
    private Float quantidadeAvaliacoes;
    private Float nota;
    private List<String> usuariosSeguidores;

}
