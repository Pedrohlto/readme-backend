package br.com.phelto.readme.postagens.application.assemblies;

import br.com.phelto.readme.postagens.application.dtos.NovaPostagemDTO;
import br.com.phelto.readme.postagens.domain.entities.Postagem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostagemAssembler {

    public Postagem toDomain(NovaPostagemDTO novaPostagemDTO) {

        return Postagem.builder()
                .usuarioPostagem(novaPostagemDTO.getUsuarioPostagem())
                .descricao(novaPostagemDTO.getDescricao())
                .titulo(novaPostagemDTO.getTitulo())
                .dataHoraPostagem(LocalDateTime.now())
                .usuariosMarcados(novaPostagemDTO.getUsuariosMarcados())
                .livros(novaPostagemDTO.getLivros())
                .nota(novaPostagemDTO.getNota())
                .tipoPostagem(novaPostagemDTO.getTipoPostagem())
                .build();
    }
}
