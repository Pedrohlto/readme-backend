package br.com.phelto.readme.usuarios.application.controllers;

import br.com.phelto.readme.usuarios.application.controllers.request.SeguirLivroRequest;
import br.com.phelto.readme.usuarios.application.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{id_livro}/")
public class LivrosSeguidoresController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void seguirLivro(@PathVariable(value="id_usuario") String idUsuario
                           ,@RequestBody SeguirLivroRequest seguirLivroRequest){
        usuariosService.seguirLivro(idUsuario, seguirLivroRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> listaLivroSeguidores(@PathVariable(value="id_usuario") String idUsuario){
        return usuariosService.listarLivroSeguidos(idUsuario);
    }

}
