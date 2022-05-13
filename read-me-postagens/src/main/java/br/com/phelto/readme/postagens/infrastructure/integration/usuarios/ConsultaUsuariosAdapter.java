package br.com.phelto.readme.postagens.infrastructure.integration.usuarios;

import br.com.phelto.readme.postagens.application.dtos.UsuarioDTO;
import br.com.phelto.readme.postagens.application.ports.ConsultaUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ConsultaUsuariosAdapter implements ConsultaUsuarios {

    @Autowired
    private ConsultaUsuariosClient consultaUsuariosClient;

    @Override
    public UsuarioDTO consultaUsuario(String idUsuario, String idUsuarioPesquisador) {
        return consultaUsuariosClient.buscarUsuarioPorId(idUsuario, idUsuarioPesquisador);
    }

    @Override
    public List<UsuarioDTO> consultaUsuarios(List<String> idsUsuarios, String idUsuarioPesquisador) {
        List<UsuarioDTO> usuarios = new ArrayList<>();

        if(Objects.nonNull(idsUsuarios)){
        idsUsuarios.forEach(id ->{
            usuarios.add(consultaUsuariosClient.buscarUsuarioPorId(id,idUsuarioPesquisador));
        });}
        return usuarios;
    }
}
