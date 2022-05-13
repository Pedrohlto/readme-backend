package br.com.phelto.readme.livros.infrastructure.repository.mysql.entities;

import br.com.phelto.readme.livros.domain.entities.Autor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="rdm_autor")
@NoArgsConstructor
public class AutorModel {

    public AutorModel(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.dataNascimento = autor.getDataNascimento();
        this.pais = autor.getPais();
    }

    @Id
    @Column(name="id_autor")
    private String id;

    @Column(name="nome")
    private String nome;

    @Column(name="dat_nascimento")
    private LocalDate dataNascimento;

    @Column(name="pais")
    private String pais;

    @Column(name="url_foto")
    private String urlFoto;

    public Autor toDomain() {
        return Autor.builder()
                .id(this.id)
                .nome(this.nome)
                .dataNascimento(this.dataNascimento)
                .pais(this.pais)
                .urlFoto(this.urlFoto)
                .build();

    }
}
