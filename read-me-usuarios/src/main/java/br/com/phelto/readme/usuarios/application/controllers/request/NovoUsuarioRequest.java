package br.com.phelto.readme.usuarios.application.controllers.request;

import br.com.phelto.readme.usuarios.domain.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovoUsuarioRequest {

    private String usuario;
    private String email;
    private String nome;
    private String bio;

    public Usuario toDomain(){
        return Usuario.builder()
                .usuario(this.usuario)
                .nome(this.nome)
                .bio(this.bio)
                .email(this.email)
                .build();
    }
}
