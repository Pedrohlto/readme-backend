package br.com.phelto.readme.postagens.domain.entities;

import br.com.phelto.readme.postagens.domain.enums.TipoPostagem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Postagem {

    private String titulo;
    private String descricao;
    @Setter
    private String usuarioPostagem;
    private LocalDateTime dataHoraPostagem;
    @Setter
    private List<String> livros;
    private TipoPostagem tipoPostagem;
    @Setter
    private List<String> usuariosMarcados;
    private Float nota;

}
