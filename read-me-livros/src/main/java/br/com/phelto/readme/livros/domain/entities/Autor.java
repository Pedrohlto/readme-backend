package br.com.phelto.readme.livros.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Autor {

    private String id;
    private String nome;
    private LocalDate dataNascimento;
    private String urlFoto;
    private String pais;
}
