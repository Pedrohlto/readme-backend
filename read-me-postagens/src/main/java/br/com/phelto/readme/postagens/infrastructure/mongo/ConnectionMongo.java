package br.com.phelto.readme.postagens.infrastructure.mongo;

import br.com.phelto.readme.postagens.infrastructure.repository.postagem.PostagemCodec;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConnectionMongo {

    @Value("${endereco.mongo}")
    public void setAddressStatic(String endereco){
        mongoEndereco = endereco;
    }

    @Value("${endereco.mongo.database}")
    public void setDBStatic(String valor){
        database = valor;
    }

    private static String mongoEndereco;
    private static String database;

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    public static MongoDatabase  getMongoDataBase(){

        if(mongoDatabase != null) return mongoDatabase;
        mongoClient = new MongoClient(mongoEndereco,montaCodecs());
        mongoDatabase = mongoClient.getDatabase(database);
        return mongoDatabase;
    }

    private static MongoClientOptions montaCodecs(){
        var postagemCodec = new PostagemCodec(MongoClient.getDefaultCodecRegistry().get(Document.class));
        var registoCodecs = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry()
                ,CodecRegistries.fromCodecs(postagemCodec));

        return MongoClientOptions.builder()
                .connectionsPerHost(5)
                .codecRegistry(registoCodecs)
                .build();
    }
}
