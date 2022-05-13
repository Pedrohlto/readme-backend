package br.com.phelto.readme.postagens.application.ports;

import br.com.phelto.readme.postagens.application.dtos.LivroDTO;

import java.util.List;

public interface ConsultaLivros {
    List<LivroDTO> consultaLivros(List<String> ids);
}
