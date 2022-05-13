package br.com.phelto.readme.postagens.infrastructure.repository.models;

import br.com.phelto.readme.postagens.application.dtos.LivroDTO;
import br.com.phelto.readme.postagens.application.dtos.PostagemDTO;
import br.com.phelto.readme.postagens.application.dtos.UsuarioDTO;
import br.com.phelto.readme.postagens.domain.enums.TipoPostagem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostagemModelNoSQL {

    public PostagemModelNoSQL(PostagemModel postagem, UsuarioDTO usuarioPostagem, List<UsuarioDTO> marcacoes, List<LivroDTO> livros) {
        this.usuario = new UsuarioModelNoSql(usuarioPostagem);
        this.idRelacional = postagem.getID();
        this.titulo = postagem.getTitulo();
        this.descricao = postagem.getDescricao();
        this.tipoPostagem = postagem.getTipoPostagem();
        this.dataHoraPostagem = LocalDateTime.now();
        this.marcacoes = marcacoes.stream().map(UsuarioModelNoSql::new).collect(Collectors.toList());
        this.livros = livros.stream().map(LivroModelNoSql::new).collect(Collectors.toList());
        this.nota = postagem.getNota();
    }

    private ObjectId id;
    private String idRelacional;
    private UsuarioModelNoSql usuario;
    private String titulo;
    private String descricao;
    private TipoPostagem tipoPostagem;
    private LocalDateTime dataHoraPostagem;
    private List<UsuarioModelNoSql> marcacoes;
    private List<LivroModelNoSql> livros;
    private Float nota;

    public PostagemModelNoSQL criarId() {
        setId(new ObjectId());
        return this;
    }

    public PostagemDTO toDTO(){
        var dateFormatter = DateTimeFormatter.ISO_DATE_TIME;
        PostagemDTO postagem = new PostagemDTO();
        postagem.setDataPostagem(this.dataHoraPostagem.format(dateFormatter).toString());
        postagem.setDescricao(this.descricao);
        postagem.setTitulo(this.titulo);
        postagem.setId(this.idRelacional);
        postagem.setUsuario(this.usuario.toDTO());
        postagem.setMarcacoes(this.marcacoes.stream().map(UsuarioModelNoSql::toDTO).collect(Collectors.toList()));
        postagem.setLivros(this.livros.stream().map(LivroModelNoSql::toDTO).collect(Collectors.toList()));
        postagem.setTipoPostagem(this.tipoPostagem.name());
        if(Objects.nonNull(this.nota))
        postagem.setNota(this.nota);
        return postagem;
    }
}
