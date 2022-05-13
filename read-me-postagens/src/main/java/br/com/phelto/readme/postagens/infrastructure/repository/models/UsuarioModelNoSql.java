package br.com.phelto.readme.postagens.infrastructure.repository.models;

import br.com.phelto.readme.postagens.application.dtos.UsuarioDTO;
import lombok.Data;

@Data
public class UsuarioModelNoSql {

    public UsuarioModelNoSql() {}

    public UsuarioModelNoSql(UsuarioDTO usuario){
        this.id = usuario.getId();
        this.usuario = usuario.getUsuario();
        this.nome = usuario.getNome();
        this.urlFoto = usuario.getUrlFoto();
    }

    private String id;
    private String usuario;
    private String nome;
    private String urlFoto;

    public UsuarioDTO toDTO(){
        return UsuarioDTO.builder()
                        .usuario(this.usuario)
                        .nome(this.nome)
                        .urlFoto(this.urlFoto)
                        .id(this.id)
                        .build();

    }
}
