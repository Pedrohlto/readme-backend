package br.com.phelto.readme.livros.domain.entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Editora {

    private String id;
    private String nome;

}
