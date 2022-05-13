package br.com.phelto.readme.postagens.infrastructure.repository.postagem;

import br.com.phelto.readme.postagens.infrastructure.repository.models.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepositoryJPA extends JpaRepository<PostagemModel, String> {

}
