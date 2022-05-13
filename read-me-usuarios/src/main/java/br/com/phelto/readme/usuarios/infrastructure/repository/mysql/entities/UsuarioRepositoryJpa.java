package br.com.phelto.readme.usuarios.infrastructure.repository.mysql.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioModel, String> {

    @Query("select c from UsuarioModel c where c.idUsuario in :listaUsuarios")
    List<UsuarioModel> buscarSeguindo(List<String> listaUsuarios);


    @Query(value = "SELECT ID_USUARIO from rdm_livros_seguindo WHERE ID_LIVRO IN (:listaDeLivros )", nativeQuery = true)
    List<String> buscarIdsUsuariosPorLivrosSeguidos(List<String> listaDeLivros);


}
