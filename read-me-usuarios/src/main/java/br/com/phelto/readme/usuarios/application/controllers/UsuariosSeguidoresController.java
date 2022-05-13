package br.com.phelto.readme.usuarios.application.controllers;

import br.com.phelto.readme.usuarios.application.controllers.request.NovoUsuarioSeguidorRequest;
import br.com.phelto.readme.usuarios.application.controllers.responses.UsuarioResponse;
import br.com.phelto.readme.usuarios.application.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/usuarios/{id_usuario}/seguidores")
public class UsuariosSeguidoresController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void seguirUsuario(@PathVariable(value="id_usuario") String idUsuario
                             ,@RequestBody NovoUsuarioSeguidorRequest novoUsuarioSeguidorRequest){
        usuariosService.seguirUsuario(idUsuario, novoUsuarioSeguidorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> listaSeguidores(@PathVariable(value="id_usuario") String idUsuario){
        return usuariosService.listarSeguidores(idUsuario);
    }

}
