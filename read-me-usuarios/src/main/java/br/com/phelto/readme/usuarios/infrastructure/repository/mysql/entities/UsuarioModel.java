package br.com.phelto.readme.usuarios.infrastructure.repository.mysql.entities;

import br.com.phelto.readme.usuarios.domain.entities.Usuario;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="RDM_USUARIO")
@Getter
public class UsuarioModel {

    public UsuarioModel(){}

    public UsuarioModel (Usuario usuario){
        this.idUsuario = UUID.randomUUID().toString();
        this.usuario = usuario.getUsuario();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
    }

    @Id
    @Column(name="ID_USUARIO")
    private String idUsuario;

    @Column(name="USUARIO")
    private String usuario;

    @Column(name="EMAIL")
    private String email;

    @Column(name="NOME")
    private String nome;

    @Column(name="BIO")
    private String bio;

    @ElementCollection
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.DELETE})
    @CollectionTable(name="RDM_LIVROS_SEGUINDO", joinColumns = @JoinColumn(name="ID_USUARIO"))
    @Column(name="ID_LIVRO")
    private Set<String> livrosSeguindo;

    @ElementCollection
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.DELETE})
    @CollectionTable(name="RDM_USUARIOS_SEGUIDORES", joinColumns = @JoinColumn(name="ID_USUARIO"))
    @Column(name="ID_USUARIO_SEGUIDOR")
    private Set<String> seguidores;

    @ElementCollection
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.DELETE})
    @CollectionTable(name="RDM_USUARIOS_SEGUIDORES", joinColumns = @JoinColumn(name="ID_USUARIO_SEGUIDOR"))
    @Column(name="ID_USUARIO")
    private Set<String> usuariosSeguidos;

    @ElementCollection
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.DELETE})
    @CollectionTable(name="RDM_FEED", joinColumns = @JoinColumn(name="ID_USUARIO"))
    @Column(name="ID_POSTAGEM")
    private Set<String> feed;

    @Column(name="URL_FOTO")
    private String idFoto;

    public Usuario toDomain (){
        return Usuario.builder().id(this.idUsuario)
                .nome(this.nome)
                .email(this.email)
                .usuario(this.usuario)
                .feed(this.feed)
                .seguidores(this.seguidores)
                .usuariosSeguindo(this.usuariosSeguidos)
                .livrosSeguindo(this.livrosSeguindo)
                .urlFoto("/photos/"+this.idFoto)
                .bio(this.bio)
                .build();
    }

}
