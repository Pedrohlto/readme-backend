create table rdm_postagem(id_postagem VARCHAR(40)
                         ,id_usuario VARCHAR(40)
                         ,titulo     VARCHAR(100)
                         ,descricao  VARCHAR(2000)
                         ,dat_hora_postagem DATETIME
                         ,tipo_postagem INT(1)
                         ,nota     FLOAT(2));

create table rdm_postagem_usr_marcados (id_postagem VARCHAR(40)
                                       ,id_usuario VARCHAR(40));

create table rdm_postagem_livro_marcados(id_postagem varchar(40)
                                        ,id_livro varchar(40));

/*Usuarios*/

create table rdm_usuario(id_usuario varchar(40)
                        ,usuario    varchar(100)
                        ,email      varchar(100)
                        ,nome       varchar(100));

create table rdm_livros_seguindo(id_usuario varchar(40)
                                ,id_livro varchar(40));

create table rdm_usuarios_seguindo(id_usuario varchar(40)
                                  ,id_usuario_seguindo varchar(40));

create table dbusuarios.rdm_feed (id_usuario VARCHAR(40)
                                 ,id_postagem VARCHAR(40));

/* new Shcema*/

create table rdm_livro(id_livro       VARCHAR(40)
                      ,isbn           VARCHAR(20)
                      ,titulo         VARCHAR(150)
                      ,autor_id       VARCHAR(40)
                      ,edicao         VARCHAR(20)
                      ,ano            INT(4)
                      ,editora_id     VARCHAR(40)
                      ,paginas        INT(4)
                      ,sinopse        VARCHAR(2000)
                      ,foto_url        VARCHAR(2000)
                      ,idioma      VARCHAR(40)
                      ,dat_publicacao DATE
                      ,qtd_leram      INT(9)
                      ,qtd_lendo      INT(9)
                      ,qtd_avaliacoes INT(9)
                      ,qtd_resenhas   INT(9));

create table rdm_autor(id_autor       VARCHAR(40)
                      ,nome           VARCHAR(100)
                      ,dat_nascimento DATE
                      ,url_foto    varchar(2000)
                      ,pais        VARCHAR(40));

create table rdm_editora(id_editora VARCHAR(40)
                        ,nome       VARCHAR(100));





