package br.com.phelto.readme.postagens.infrastructure.repository.postagem;

import br.com.phelto.readme.postagens.application.dtos.LivroDTO;
import br.com.phelto.readme.postagens.application.dtos.PagePostagem;
import br.com.phelto.readme.postagens.application.dtos.PostagemDTO;
import br.com.phelto.readme.postagens.application.dtos.UsuarioDTO;
import br.com.phelto.readme.postagens.application.ports.ConsultaLivros;
import br.com.phelto.readme.postagens.application.ports.ConsultaUsuarios;
import br.com.phelto.readme.postagens.application.query.PostagemQuerys;
import br.com.phelto.readme.postagens.domain.entities.Postagem;
import br.com.phelto.readme.postagens.domain.repository.PostagemRepository;
import br.com.phelto.readme.postagens.infrastructure.mongo.ConnectionMongo;
import br.com.phelto.readme.postagens.infrastructure.repository.models.PostagemModel;
import br.com.phelto.readme.postagens.infrastructure.repository.models.PostagemModelNoSQL;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class PostagemRepositoryImpl implements PostagemRepository, PostagemQuerys {

    @Autowired
    private PostagemRepositoryJPA jpa;

    @Autowired
    private ConsultaUsuarios consultaUsuarios;

    @Autowired
    private ConsultaLivros consultaLivros;

    @Override
    @Transactional
    public PostagemDTO save(Postagem postagem) {

        var livros = consultaLivros.consultaLivros(postagem.getLivros());
        var usuarioPostagem = consultaUsuarios.consultaUsuario(postagem.getUsuarioPostagem(), postagem.getUsuarioPostagem());

        var marcacoes = consultaUsuarios.consultaUsuarios(postagem.getUsuariosMarcados(),postagem.getUsuarioPostagem());

        var postagemModel = saveRelational(postagem);

        return saveNoSql(postagemModel, usuarioPostagem, marcacoes, livros).toDTO();
    }

    private PostagemModelNoSQL saveNoSql(PostagemModel postagemModel, UsuarioDTO usuarioPostagem, List<UsuarioDTO> marcacoes, List<LivroDTO> livros) {
        MongoDatabase bancoDeDados = ConnectionMongo.getMongoDataBase();
        var postagemModelNoSQL = new PostagemModelNoSQL(postagemModel, usuarioPostagem, marcacoes, livros);
        MongoCollection<PostagemModelNoSQL> postagens = bancoDeDados.getCollection("postagens", PostagemModelNoSQL.class);
        postagens.insertOne(postagemModelNoSQL);
        return postagemModelNoSQL;
    }

    private PostagemModel saveRelational(Postagem postagem) {
        PostagemModel postagemModel = new PostagemModel(postagem);
        return jpa.save(postagemModel);
    }

    @Override
    public PagePostagem listarPostagem(Integer pagina, Integer tamanho, String idUsuario) {
        List<PostagemDTO> listaPostagem = new ArrayList<>();
        var dataBase = ConnectionMongo.getMongoDataBase();
        var collection = dataBase.getCollection("postagens", PostagemModelNoSQL.class);
        Long total = 0L;
        MongoCursor<PostagemModelNoSQL> mongoCursor = null;
        if (!Objects.isNull(idUsuario)) {
            mongoCursor = collection.find(eq("usuarioCriacao.id", idUsuario))
                          .skip(tamanho * (pagina - 1)).sort(Sorts.descending("dataPostagem")).limit(tamanho).iterator();

        } else {
            mongoCursor = collection.find()
                    .skip(tamanho * (pagina - 1)).sort(Sorts.descending("dataPostagem")).limit(tamanho).iterator();
        }

        while(mongoCursor.hasNext()){
            total += 1;
            listaPostagem.add(mongoCursor.next().toDTO());
        }
        return new PagePostagem(pagina, tamanho, Integer.valueOf(Math.round(total/tamanho)) +1, total, listaPostagem);
    }

    @Override
    public PagePostagem listarPostagemPorLivro(Integer pagina, Integer tamanho, String idLivro) {
        List<PostagemDTO> listaPostagem = new ArrayList<>();
        var dataBase = ConnectionMongo.getMongoDataBase();
        var collection = dataBase.getCollection("postagens", PostagemModelNoSQL.class);
        Long total = 0L;

        var mongoCursor = collection.find(eq("livros.id", idLivro))
                    .skip(tamanho * (pagina - 1)).sort(Sorts.descending("dataPostagem")).limit(tamanho).iterator();

        while(mongoCursor.hasNext()){
            total += 1;
            listaPostagem.add(mongoCursor.next().toDTO());
        }
        return new PagePostagem(pagina, tamanho, Integer.valueOf(Math.round(total/tamanho)) +1, total, listaPostagem);
    }

    @Override
    @Cacheable(value = "postagem"
              ,key="#idPostagem"
              ,cacheManager = "cacheManager3Horas")
    public PostagemDTO getPostagemById(String idPostagem) {
        System.out.println("Acessei sem cache");
        var dataBase = ConnectionMongo.getMongoDataBase();
        var collection = dataBase.getCollection("postagens", PostagemModelNoSQL.class);
        var resultado = collection.find(eq("idRelacional", idPostagem)).first();
        return resultado.toDTO();
    }



}
