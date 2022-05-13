package br.com.phelto.readme.postagens.infrastructure.repository.postagem;

import br.com.phelto.readme.postagens.domain.enums.TipoPostagem;
import br.com.phelto.readme.postagens.infrastructure.repository.models.LivroModelNoSql;
import br.com.phelto.readme.postagens.infrastructure.repository.models.PostagemModelNoSQL;
import br.com.phelto.readme.postagens.infrastructure.repository.models.UsuarioModelNoSql;
import lombok.NoArgsConstructor;
import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class PostagemCodec implements CollectibleCodec<PostagemModelNoSQL> {

    private Codec<Document> codec;

    public PostagemCodec(Codec<Document> codec){
        this.codec = codec;
    }

    @Override
    public void encode(BsonWriter bsonWriter, PostagemModelNoSQL postagemModelNoSQL, EncoderContext encoderContext) {
        var id = postagemModelNoSQL.getId();
        var idRelacional = postagemModelNoSQL.getIdRelacional();
        var titulo = postagemModelNoSQL.getTitulo();
        var descricao = postagemModelNoSQL.getDescricao();
        var dataPostagem = postagemModelNoSQL.getDataHoraPostagem();
        var usuarioCriacao = postagemModelNoSQL.getUsuario();
        var listaDeMarcacoes = postagemModelNoSQL.getMarcacoes();
        var listaDeLivros = postagemModelNoSQL.getLivros();
        var tipoPostagem = postagemModelNoSQL.getTipoPostagem();

        var documentoMongo = new Document();
        documentoMongo.put("_id",id);
        documentoMongo.put("idRelacional", idRelacional);
        documentoMongo.put("titulo",titulo);
        documentoMongo.put("descricao",descricao);
        documentoMongo.put("dataPostagem",dataPostagem);
        documentoMongo.put("usuarioCriacao", montaDocumentoUsuario(usuarioCriacao));
        documentoMongo.put("usuariosMarcados", listaDeMarcacoes.stream().map(usuario -> montaDocumentoUsuario(usuario)).collect(Collectors.toList()));
        documentoMongo.put("livros", listaDeLivros.stream().map(livro -> montaDocumentoLivro(livro)).collect(Collectors.toList()));
        documentoMongo.put("tipoPostagem",tipoPostagem.name());
        documentoMongo.put("nota",postagemModelNoSQL.getNota());
        codec.encode(bsonWriter, documentoMongo, encoderContext);
    }

    @Override
    public PostagemModelNoSQL decode(BsonReader bsonReader, DecoderContext decoderContext) {
        UsuarioModelNoSql usuarioCriaca = new UsuarioModelNoSql();
        List<UsuarioModelNoSql> usuariosMarcados = new ArrayList<>();
        List<LivroModelNoSql> livros = new ArrayList<>();

        var document = codec.decode(bsonReader, decoderContext);

        PostagemModelNoSQL postagem = new PostagemModelNoSQL();
        postagem.setId(document.getObjectId("_id"));
        postagem.setIdRelacional(document.getString("idRelacional"));
        postagem.setTitulo(document.getString("titulo"));
        postagem.setDescricao(document.getString("descricao"));
        postagem.setDataHoraPostagem(document.getDate("dataPostagem").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        postagem.setTipoPostagem(TipoPostagem.valueOf(document.getString("tipoPostagem")));

        Document usuarioCriacaoDocument = (Document) document.get("usuarioCriacao");

        if(usuarioCriacaoDocument != null){
            usuarioCriaca.setUsuario(usuarioCriacaoDocument.getString("usuario"));
            usuarioCriaca.setId(usuarioCriacaoDocument.getString("id"));
            usuarioCriaca.setNome(usuarioCriacaoDocument.getString("nome"));
            usuarioCriaca.setUrlFoto(usuarioCriacaoDocument.getString("urlFoto"));
            postagem.setUsuario(usuarioCriaca);
        }

        List<Document> usuariosMarcadosDocument = (List<Document>) document.get("usuariosMarcados");
        usuariosMarcadosDocument.forEach(usuarios ->{
            UsuarioModelNoSql user = new UsuarioModelNoSql();
            user.setUsuario(usuarios.getString("usuario"));
            user.setId(usuarios.getString("id"));
            user.setNome(usuarios.getString("nome"));
            user.setUrlFoto(usuarios.getString("urlFoto"));
            usuariosMarcados.add(user);
        });

        postagem.setMarcacoes(usuariosMarcados);

        List<Document> livrosMarcadosDocument = (List<Document>) document.get("livros");

        livrosMarcadosDocument.forEach(livroDocument ->{
            LivroModelNoSql livro = new LivroModelNoSql();
            livro.setNome(livroDocument.getString("nome"));
            livro.setIsbn(livroDocument.getString("isbn"));
            livro.setId(livroDocument.getString("id"));
            livro.setAutor(livroDocument.getString("autor"));
            livro.setUrlFoto(livroDocument.getString("urlFoto"));
            livros.add(livro);
        });

        postagem.setLivros(livros);
        var nota = document.getDouble("nota");

        if(Objects.nonNull(nota))
        postagem.setNota(nota.floatValue());

        return postagem;
    }

    @Override
    public PostagemModelNoSQL generateIdIfAbsentFromDocument(PostagemModelNoSQL postagemModelNoSQL) {
        return documentHasId(postagemModelNoSQL) ? postagemModelNoSQL : postagemModelNoSQL.criarId();
    }

    @Override
    public boolean documentHasId(PostagemModelNoSQL postagemModelNoSQL) {
        return postagemModelNoSQL.getId() != null;
    }

    @Override
    public BsonValue getDocumentId(PostagemModelNoSQL postagemModelNoSQL) {
        if(!documentHasId(postagemModelNoSQL)) throw new IllegalArgumentException("Documento não tem ID");
        return new BsonString(postagemModelNoSQL.getId().toHexString());
    }

    @Override
    public Class<PostagemModelNoSQL> getEncoderClass() {
        return PostagemModelNoSQL.class;
    }

    private Document montaDocumentoUsuario(UsuarioModelNoSql usuarioModel){
        var documento = new Document();
        documento.put("id",usuarioModel.getId());
        documento.put("usuario",usuarioModel.getUsuario());
        documento.put("nome", usuarioModel.getNome());
        documento.put("urlFoto",usuarioModel.getUrlFoto());
        return documento;
    }

    private Document montaDocumentoLivro(LivroModelNoSql livroModelNoSql){
        var documento = new Document();
        documento.put("id",livroModelNoSql.getId());
        documento.put("isbn",livroModelNoSql.getIsbn());
        documento.put("nome", livroModelNoSql.getNome());
        documento.put("autor",livroModelNoSql.getAutor());
        documento.put("urlFoto",livroModelNoSql.getUrlFoto());
        return documento;
    }
}
