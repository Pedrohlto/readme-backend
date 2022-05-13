package br.com.phelto.readme.usuarios.infrastructure.repository.elastic;

import br.com.phelto.readme.usuarios.infrastructure.repository.elastic.entities.UsuarioElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioElasticRepository extends ElasticsearchRepository<UsuarioElastic, String> {

    Optional<UsuarioElastic> findByUsuarioLike(String usuario);

    List<UsuarioElastic> findByUsuarioIgnoreCaseContainsOrNomeContainsIgnoreCase(String pesquisaUsuario, String pesquisaNome);
}
