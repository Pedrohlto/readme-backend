package br.com.phelto.readme.usuarios.application.controllers;

import br.com.phelto.readme.usuarios.application.controllers.request.NovaPostagemRequest;
import br.com.phelto.readme.usuarios.application.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @PostMapping
    public void inserePostagemFeed(@RequestBody NovaPostagemRequest novaPostagemRequest){
        feedService.insereNovaPostagem(novaPostagemRequest);
    }
}
