package br.com.phelto.readme.postagens.infrastructure.integration.usuarios;

import br.com.phelto.readme.postagens.application.dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="Consulta-usuarios" ,url = "${usuarios.url}")
public interface ConsultaUsuariosClient {

    @GetMapping("/{id_usuario}")
    UsuarioDTO buscarUsuarioPorId(@PathVariable("id_usuario") String idUsuario,
                                  @RequestParam("me") String idPesquisador);
}
