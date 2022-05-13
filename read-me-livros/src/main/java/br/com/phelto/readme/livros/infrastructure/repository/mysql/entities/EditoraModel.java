package br.com.phelto.readme.livros.infrastructure.repository.mysql.entities;

import br.com.phelto.readme.livros.domain.entities.Editora;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rdm_editora")
@NoArgsConstructor
public class EditoraModel {

    public EditoraModel(Editora editora) {
        this.id = editora.getId();
        this.nome = editora.getNome();
    }

    @Id
    @Column(name="id_editora")
    private String id;

    @Column(name="nome")
    private String nome;

    public Editora toDomain(){
       return Editora.builder().id(this.id).nome(this.nome).build();
    }
}
