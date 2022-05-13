package br.com.phelto.readme.usuarios.application.services;

import br.com.phelto.readme.usuarios.application.controllers.request.NovoUsuarioRequest;
import br.com.phelto.readme.usuarios.application.controllers.request.SeguirLivroRequest;
import br.com.phelto.readme.usuarios.application.controllers.request.NovoUsuarioSeguidorRequest;
import br.com.phelto.readme.usuarios.application.controllers.responses.UsuarioResponse;
import br.com.phelto.readme.usuarios.domain.repository.UsuarioRepository;
import br.com.phelto.readme.usuarios.infrastructure.repository.elastic.UsuarioElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosService {

    @Autowired
    private UsuarioElasticRepository usuarioElasticRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse inserirUsuario(NovoUsuarioRequest request) {
        var usuario = request.toDomain();
        var usuarioInserido = usuarioRepository.incluir(usuario);
        return new UsuarioResponse(usuarioInserido);
    }

    public void seguirUsuario(String idUsuario, NovoUsuarioSeguidorRequest usuarioSeguidor){
        usuarioRepository.seguirUsuario(idUsuario, usuarioSeguidor.getIdUsuario());
    }

    public void seguirLivro(String idUsuario, SeguirLivroRequest seguirLivro){
        usuarioRepository.seguirLivro(idUsuario, seguirLivro.getIdLivro());
    }

//    public List<UsuarioResponse> listarTodosUsuarios(){
//        var usuariosElastics = usuarioElasticRepository.findAll();
//
//       return StreamSupport.stream(usuariosElastics.spliterator(), false)
//                .collect(Collectors.toList())
//                .stream().map(UsuarioResponse::new)
//                .collect(Collectors.toList());
//    }

    public List<UsuarioResponse> listarTodosUsuarios(){
        var usuarios = usuarioRepository.listarUsuarios();
        return usuarios.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }

    public List<UsuarioResponse> listarUsuarios(String nome){
        var usuariosElastics = usuarioElasticRepository.findByUsuarioIgnoreCaseContainsOrNomeContainsIgnoreCase(nome,nome);
        return usuariosElastics.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }

    /*public UsuarioResponse buscarUsuarioPorId(String idUsuario){
        var usuarioElastic = usuarioElasticRepository.findById(idUsuario);
        if(usuarioElastic.isPresent())
        return new UsuarioResponse(usuarioElastic.get());
        throw new RuntimeException("Usuario não encontrado");
    }*/

    public UsuarioResponse buscarUsuarioPorId(String idUsuarioPesquisador,String idUsuario){
        var usuario = usuarioRepository.detalharUsuario(idUsuario);
        var usuarioResponse = new UsuarioResponse(usuario);
        usuarioResponse.setEstouSeguindo(usuario.getSeguidores().contains(idUsuarioPesquisador));
        return usuarioResponse;
    }


    public List<UsuarioResponse> listarSeguidores(String idUsuario) {
        var listaSeguindo = usuarioRepository.listarSeguidores(idUsuario);
        return listaSeguindo.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }

    public List<String> listarLivroSeguidos(String idUsuario) {
        var listaSeguindo = usuarioRepository.listarLivrosSeguindo(idUsuario);
        return listaSeguindo;
    }

    public List<UsuarioResponse> listarSeguindo(String idUsuario) {
        var listaSeguindo = usuarioRepository.listarSeguindo(idUsuario);
        return listaSeguindo.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }
}
