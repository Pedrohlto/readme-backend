package br.com.phelto.readme.livros.infrastructure.repository.mysql.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepositoryJpa extends JpaRepository<LivroModel, String> {

    @Query("SELECT c FROM LivroModel c WHERE UPPER(c.titulo) like CONCAT('%',UPPER(:nomeLivro),'%')")
    List<LivroModel> buscarPorNome(String nomeLivro);
}
