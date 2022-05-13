package br.com.phelto.readme.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class ReadMeUsuariosApplication {
//TODO Enriquecer usuário com quantidade de usuarios que segue e etc.
//TODO Mudar datas UTC
//TODO URL das fotos
	public static void main(String[] args) {
		SpringApplication.run(ReadMeUsuariosApplication.class, args);
	}

}
