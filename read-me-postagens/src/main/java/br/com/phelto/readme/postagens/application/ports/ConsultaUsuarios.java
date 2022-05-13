package br.com.phelto.readme.postagens.application.ports;

import br.com.phelto.readme.postagens.application.dtos.UsuarioDTO;

import java.util.List;

public interface ConsultaUsuarios {

    UsuarioDTO consultaUsuario(String idUsuario, String idUsuarioPesquisador);
    List<UsuarioDTO> consultaUsuarios(List<String> idsUsuarios, String idUsuarioPesquisador);
}
