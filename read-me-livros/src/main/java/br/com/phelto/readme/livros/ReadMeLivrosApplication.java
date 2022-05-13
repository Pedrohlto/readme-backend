package br.com.phelto.readme.livros;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadMeLivrosApplication {

	private static final Logger logger = LogManager.getLogger(ReadMeLivrosApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(ReadMeLivrosApplication.class, args);
		logger.info("teste de envio logs");
	}

}
