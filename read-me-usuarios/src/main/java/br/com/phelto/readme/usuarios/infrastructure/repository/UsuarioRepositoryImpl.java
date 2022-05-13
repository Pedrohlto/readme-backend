package br.com.phelto.readme.usuarios.infrastructure.repository;

import br.com.phelto.readme.usuarios.application.controllers.request.NovaPostagemRequest;
import br.com.phelto.readme.usuarios.domain.entities.Usuario;
import br.com.phelto.readme.usuarios.domain.repository.UsuarioRepository;
import br.com.phelto.readme.usuarios.infrastructure.repository.elastic.UsuarioElasticRepository;
import br.com.phelto.readme.usuarios.infrastructure.repository.elastic.entities.UsuarioElastic;
import br.com.phelto.readme.usuarios.infrastructure.repository.mysql.entities.UsuarioModel;
import br.com.phelto.readme.usuarios.infrastructure.repository.mysql.entities.UsuarioRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private UsuarioElasticRepository usuarioElasticRepository;

    @Autowired
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @Override
    public Usuario incluir(Usuario usuario) {
        UsuarioModel usuarioModel = new UsuarioModel(usuario);
        var usuarioInserido = usuarioRepositoryJpa.save(usuarioModel);
        UsuarioElastic usuarioElastic = new UsuarioElastic(usuarioInserido);
        usuarioElasticRepository.save(usuarioElastic);
        return usuarioInserido.toDomain();
    }

    @Override
    @Transactional
    public void seguirUsuario(String idUsuario, String novoSeguidor) {
        var usuario = usuarioRepositoryJpa.findById(idUsuario)
                .orElseThrow(()-> new IllegalArgumentException());

        if(usuario.getSeguidores().contains(novoSeguidor)) {
            usuario.getSeguidores().remove(novoSeguidor);
        } else{
            usuario.getSeguidores().add(novoSeguidor);
        }

        var seguidor = usuarioRepositoryJpa.findById(novoSeguidor)
                .orElseThrow(()-> new IllegalArgumentException());

        if(seguidor.getUsuariosSeguidos().contains(idUsuario)){
            seguidor.getUsuariosSeguidos().remove(idUsuario);
        } else {
            seguidor.getUsuariosSeguidos().add(idUsuario);
        }
    }

    @Override
    @Transactional
    public void seguirLivro(String idUsuario, String idLivro){
        var usuario = usuarioRepositoryJpa.findById(idUsuario)
                .orElseThrow(()-> new IllegalArgumentException());
        usuario.getLivrosSeguindo().add(idLivro);
    }

    @Override
    public List<Usuario> listarSeguidores(String idUsuario) {
        var usuario = usuarioRepositoryJpa.findById(idUsuario);
        var listaSeguindo = usuario.get().getSeguidores();

        return usuarioRepositoryJpa.buscarSeguindo(new ArrayList<>(listaSeguindo))
                .stream()
                .map(UsuarioModel::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listarLivrosSeguindo(String idUsuario) {
        var usuario = usuarioRepositoryJpa.findById(idUsuario);
        var listaSeguindo = usuario.get().getLivrosSeguindo();
        return new ArrayList<>(listaSeguindo);
    }

    @Override
    @Transactional
    public void inserirPostagem(NovaPostagemRequest novaPostagemRequest) {
        var livros = novaPostagemRequest.getLivros();
        var usuariosIds = usuarioRepositoryJpa.buscarIdsUsuariosPorLivrosSeguidos(livros);
        var usuarios = usuarioRepositoryJpa.findAllById(usuariosIds);
        usuarios.forEach(usuario ->{
            usuario.getFeed().add(novaPostagemRequest.getIdPostagem());
        });
    }

    @Override
    public Usuario detalharUsuario(String idUsuario) {
        return usuarioRepositoryJpa.findById(idUsuario).get().toDomain();
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositoryJpa.findAll().stream().map(UsuarioModel::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarSeguindo(String idUsuario) {
        var usuario = usuarioRepositoryJpa.findById(idUsuario);
        var listaSeguindo = usuario.get().getUsuariosSeguidos();

        return usuarioRepositoryJpa.buscarSeguindo(new ArrayList<>(listaSeguindo))
                .stream()
                .map(UsuarioModel::toDomain)
                .collect(Collectors.toList());
    }
}
