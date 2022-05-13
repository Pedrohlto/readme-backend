package br.com.phelto.readme.usuarios.application.services;

import br.com.phelto.readme.usuarios.application.controllers.request.NovaPostagemRequest;
import br.com.phelto.readme.usuarios.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void insereNovaPostagem(NovaPostagemRequest novaPostagemRequest){
       usuarioRepository.inserirPostagem(novaPostagemRequest);
    }

}
