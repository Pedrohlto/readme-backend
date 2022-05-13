package br.com.phelto.readme.postagens.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagePostagem {

    private Integer pagina;
    private Integer tamanho;
    private Integer paginas;
    private Long total;
    private List<PostagemDTO> postagens;
}
