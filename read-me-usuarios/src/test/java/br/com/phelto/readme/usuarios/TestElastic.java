package br.com.phelto.readme.usuarios;

import br.com.phelto.readme.usuarios.infrastructure.repository.elastic.UsuarioElasticRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestElastic {

    @Autowired
    private UsuarioElasticRepository usuarioElasticRepository;

    @Test
    void testElasticSearch(){
        var usuarios = usuarioElasticRepository.findAll();
        usuarios.forEach(usuario ->{
            System.out.println(usuario.getUsuario());
        });
    }
}
