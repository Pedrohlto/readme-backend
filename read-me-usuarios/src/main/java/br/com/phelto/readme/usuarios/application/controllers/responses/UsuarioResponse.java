package br.com.phelto.readme.usuarios.application.controllers.responses;

import br.com.phelto.readme.usuarios.domain.entities.Usuario;
import br.com.phelto.readme.usuarios.infrastructure.repository.elastic.UsuarioElasticRepository;
import br.com.phelto.readme.usuarios.infrastructure.repository.elastic.entities.UsuarioElastic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UsuarioResponse {

    public UsuarioResponse(Usuario usuario){
        this.id = usuario.getId();
        this.usuario = usuario.getUsuario();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.bio = usuario.getBio();
        this.usuariosSeguindo = !Objects.isNull(usuario.getUsuariosSeguindo()) ? usuario.getUsuariosSeguindo().size() : 0L;
        this.livrosSeguindo = !Objects.isNull(usuario.getLivrosSeguindo()) ? usuario.getLivrosSeguindo().size() : 0L;
        this.seguindo = usuariosSeguindo;
        this.seguidores = !Objects.isNull(usuario.getSeguidores()) ? usuario.getSeguidores().size() : 0L;
        this.urlFoto = usuario.getUrlFoto();
    }

    public UsuarioResponse(UsuarioElastic usuarioElastic){
        this.id = usuarioElastic.getId();
        this.usuario = usuarioElastic.getUsuario();
        this.email = usuarioElastic.getEmail();
        this.nome = usuarioElastic.getNome();

    }

    private String id;
    private String usuario;
    private String email;
    private String nome;
    private String bio;
    private Long seguindo;
    private Long seguidores;
    private String urlFoto;
    private boolean estouSeguindo;

    @JsonIgnore
    private Long usuariosSeguindo;
    @JsonIgnore
    private Long livrosSeguindo;


}
