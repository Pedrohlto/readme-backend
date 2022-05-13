package br.com.phelto.readme.usuarios.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class Usuario {

    private String id;
    private String usuario;
    private String email;
    private String nome;
    private String bio;
    private String urlFoto;
    private Set<String> seguidores;
    private Set<String> livrosSeguindo = new HashSet<>();
    private Set<String> usuariosSeguindo = new HashSet<>();
    private Set<String> feed = new HashSet<>();
}
