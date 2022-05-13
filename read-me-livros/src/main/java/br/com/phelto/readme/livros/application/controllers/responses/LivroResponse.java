package br.com.phelto.readme.livros.application.controllers.responses;

import br.com.phelto.readme.livros.domain.entities.Livro;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class LivroResponse {

    public LivroResponse(Livro livro){
        this.id = livro.getId();
        this.isbn = livro.getIsbn();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor().getNome();
        this.edicao = livro.getEdicao();
        this.ano = livro.getAno();
        this.editora = livro.getEditora().getNome();
        this.paginas = livro.getPaginas();
        this.fotoUrl = "/photos/"+livro.getFotoUrl();
        this.sinopse = livro.getSinopse();
        this.dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ISO_DATE);
        this.idioma = livro.getIdioma();
        this.avaliacao = 5;
        this.quantidadeAvaliacao = 1;
    }

    private String id;
    private String isbn;
    private String titulo;
    private String autor;
    private String edicao;
    private Integer ano;
    private String editora;
    private Integer paginas;
    private String fotoUrl;
    private String sinopse;
    private String dataPublicacao;
    private String idioma;
    private Integer avaliacao;
    private Integer quantidadeAvaliacao;

}
