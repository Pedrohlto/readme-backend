package br.com.phelto.readme.postagens.application.dtos;

import br.com.phelto.readme.postagens.domain.enums.TipoPostagem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NovaPostagemDTO {

    private String titulo;
    private String descricao;
    private String usuarioPostagem;
    private TipoPostagem tipoPostagem;
    private Float nota;
    private List<String> livros;
    private List<String> usuariosMarcados;

}
