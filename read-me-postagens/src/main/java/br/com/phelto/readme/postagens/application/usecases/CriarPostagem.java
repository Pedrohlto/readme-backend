package br.com.phelto.readme.postagens.application.usecases;

import br.com.phelto.readme.postagens.application.assemblies.PostagemAssembler;
import br.com.phelto.readme.postagens.application.dtos.NovaPostagemDTO;
import br.com.phelto.readme.postagens.application.dtos.PostagemDTO;
import br.com.phelto.readme.postagens.application.events.PostagemCriadaEvent;
import br.com.phelto.readme.postagens.domain.entities.Postagem;
import br.com.phelto.readme.postagens.domain.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.stereotype.Service;

@Service
public class CriarPostagem extends AbstractAggregateRoot<CriarPostagem> {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private PostagemAssembler postagemAssembler;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public PostagemDTO execute(NovaPostagemDTO novaPostagemDTO){
        var postagem  = postagemAssembler.toDomain(novaPostagemDTO);
        var postagemCriadaDto = postagemRepository.save(postagem);
        criaEventoPostagemCriada(postagem, postagemCriadaDto);

        return postagemCriadaDto;
    }

    private void criaEventoPostagemCriada(Postagem postagem, PostagemDTO postagemCriadaDto) {
        applicationEventPublisher.publishEvent(new PostagemCriadaEvent(this, postagemCriadaDto.getId(),
                                              postagem.getLivros(),
                                              postagem.getUsuariosMarcados()));
    }

}
