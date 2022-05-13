package br.com.phelto.readme.livros.infrastructure.repository.mysql.entities;

import br.com.phelto.readme.livros.domain.entities.Livro;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="rdm_livro")
@NoArgsConstructor
@Data
public class LivroModel {

    public LivroModel(Livro livro){
        this.id = UUID.randomUUID().toString();
        this.isbn = livro.getIsbn();
        this.titulo = livro.getTitulo();
        this.autor = new AutorModel(livro.getAutor());
        this.edicao = livro.getEdicao();
        this.ano = livro.getAno();
        this.editora = new EditoraModel(livro.getEditora());
        this.paginas = livro.getPaginas();
        this.fotoUrl = livro.getFotoUrl();
        this.sinopse = livro.getSinopse();
        this.dataPublicacao = livro.getDataPublicacao();
        this.idioma = livro.getIdioma();
    }

    @Id
    @Column(name="id_livro")
    public String id;

    @Column(name="isbn")
    private String isbn;

    @Column(name="titulo")
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorModel autor;

    @Column(name="edicao")
    private String edicao;

    @Column(name="ano")
    private Integer ano;

    @ManyToOne
    @JoinColumn(name="editora_id")
    private EditoraModel editora;

    @Column(name="paginas")
    private Integer paginas;

    @Column(name="foto_url")
    private String fotoUrl;

    @Column(name="sinopse")
    private String sinopse;

    @Column(name="dat_publicacao")
    private LocalDate dataPublicacao;

    @Column(name="idioma")
    private String idioma;

    @ElementCollection
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.DELETE})
    @CollectionTable(name="RDM_LIVRO_SEGUIDORES", joinColumns = @JoinColumn(name="ID_LIVRO"))
    @Column(name="ID_USUARIO_SEGUIDOR")
    private List<String> seguidores;


    public Livro toDomain() {
        return Livro.builder()
                .id(this.id)
                .titulo(this.titulo)
                .ano(this.ano)
                .autor(this.autor.toDomain())
                .dataPublicacao(this.dataPublicacao)
                .edicao(this.edicao)
                .fotoUrl(this.fotoUrl)
                .isbn(this.isbn)
                .paginas(this.paginas)
                .sinopse(this.sinopse)
                .Idioma(this.idioma)
                .editora(this.editora.toDomain())
                .build();

    }
}
