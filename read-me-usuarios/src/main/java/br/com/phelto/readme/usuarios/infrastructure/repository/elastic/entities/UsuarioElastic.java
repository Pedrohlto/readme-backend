package br.com.phelto.readme.usuarios.infrastructure.repository.elastic.entities;

import br.com.phelto.readme.usuarios.infrastructure.repository.mysql.entities.UsuarioModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "catalago")
@Getter
@Setter
public class UsuarioElastic {

    public UsuarioElastic(){}

    public UsuarioElastic(UsuarioModel usuario){
        this.id = usuario.getIdUsuario();
        this.usuario = usuario.getUsuario();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
    }

    @Id
    private String id;

    @Field(name="usuario", type = FieldType.Text)
    private String usuario;

    @Field(name="email", type = FieldType.Text)
    private String email;

    @Field(name="nome", type = FieldType.Text)
    private String nome;

}
