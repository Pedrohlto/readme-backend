package br.com.phelto.readme.postagens.infrastructure.repository.models;

import br.com.phelto.readme.postagens.application.dtos.LivroDTO;
import lombok.Data;

@Data
public class LivroModelNoSql {

    public LivroModelNoSql() {}

    public LivroModelNoSql(LivroDTO livro){
        this.id = livro.getId();
        this.isbn = livro.getIsbn();
        this.nome = livro.getTitulo();
        this.autor = livro.getAutor();
        this.urlFoto = livro.getFotoUrl();
    }

    private String id;
    private String isbn;
    private String nome;
    private String autor;
    private String urlFoto;

    public LivroDTO toDTO() {
        LivroDTO livro = new LivroDTO();
        livro.setAutor(this.autor);
        livro.setIsbn(this.isbn);
        livro.setTitulo(this.nome);
        livro.setId(this.id);
        livro.setFotoUrl(this.urlFoto);
        return livro;
    }
}
