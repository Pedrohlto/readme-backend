package br.com.phelto.readme.postagens.application.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PostagemDTO implements Serializable {

    private String id;
    private String titulo;
    private String descricao;
    private String tipoPostagem;
    private String dataPostagem;
    private float nota;
    private UsuarioDTO usuario;
    private List<UsuarioDTO> marcacoes;
    private List<LivroDTO> livros;

}
