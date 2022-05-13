package br.com.phelto.readme.usuarios.application.controllers;

import br.com.phelto.readme.usuarios.application.controllers.request.NovoUsuarioRequest;
import br.com.phelto.readme.usuarios.application.controllers.responses.UsuarioResponse;
import br.com.phelto.readme.usuarios.application.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    public UsuarioResponse inserirUsuario(@RequestBody NovoUsuarioRequest request){
        return usuariosService.inserirUsuario(request);
    }

    @GetMapping("/{id_usuario}")
    public UsuarioResponse buscaUsuarioPorUsuario(@PathVariable("id_usuario") String idUsuario,
                                                  @RequestParam("me") String idUsuarioPesquisador) {
        return usuariosService.buscarUsuarioPorId(idUsuarioPesquisador, idUsuario);
    }

    @GetMapping("/{id_usuario}/seguindo")
    public List<UsuarioResponse> buscarUsuariosSeguindo(@PathVariable("id_usuario") String idUsuario) {
        return usuariosService.listarSeguindo(idUsuario);
    }

    @GetMapping
    public List<UsuarioResponse> listarUsuarios(@RequestParam(value = "pesquisa", required = false) String nome){
        if(nome != null)
        return usuariosService.listarUsuarios(nome);
        return usuariosService.listarTodosUsuarios();
    }
}
