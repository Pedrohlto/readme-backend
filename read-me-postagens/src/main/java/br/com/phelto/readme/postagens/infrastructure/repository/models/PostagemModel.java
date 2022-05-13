package br.com.phelto.readme.postagens.infrastructure.repository.models;

import br.com.phelto.readme.postagens.domain.entities.Postagem;
import br.com.phelto.readme.postagens.domain.enums.TipoPostagem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="RDM_POSTAGEM")
@NoArgsConstructor
@Getter
public class PostagemModel {

    public PostagemModel (Postagem postagem){
        this.ID = geraId(postagem);
        this.idUSuario = postagem.getUsuarioPostagem();
        this.titulo = postagem.getTitulo();
        this.descricao = postagem.getDescricao();
        this.nota = postagem.getNota();
        this.tipoPostagem = postagem.getTipoPostagem();
        this.dataHoraPostagem = LocalDateTime.now();
        this.usuariosMarcados = postagem.getUsuariosMarcados();
        this.livros = postagem.getLivros();
    }

    @Id
    @Column(name="ID_POSTAGEM")
    private String ID;

    @Column(name="ID_USUARIO")
    private String idUSuario;

    @Column(name="TITULO")
    private String titulo;

    @Column(name="DESCRICAO")
    private String descricao;

    @Column(name="DAT_HORA_POSTAGEM")
    private LocalDateTime dataHoraPostagem;

    @Column(name="TIPO_POSTAGEM")
    @Enumerated(EnumType.ORDINAL)
    private TipoPostagem tipoPostagem;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "RDM_POSTAGEM_USR_MARCADOS", joinColumns = @JoinColumn(name = "ID_USUARIO"))
    @Column(name="ID_POSTAGEM")
    private List<String> usuariosMarcados;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "RDM_POSTAGEM_LIVRO_MARCADOS", joinColumns = @JoinColumn(name = "ID_LIVRO"))
    @Column(name="ID_POSTAGEM")
    private List<String> livros;

    @Column(name="NOTA")
    private Float nota;

    private String geraId(Postagem postagem) {
        return UUID.randomUUID().toString();
    }

}
