package br.com.phelto.readme.postagens.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsuarioDTO implements Serializable {

    private String id;
    private String usuario;
    private String nome;
    private String urlFoto;
}
