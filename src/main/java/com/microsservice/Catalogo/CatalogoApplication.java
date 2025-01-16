package com.microsservice.Catalogo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CatalogoApplication {
	
    private static final String URL_APP = "url.aplicacao";
    private static final String SERVER_PORT = "server.port";
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogoApplication.class);
    
	public static void main(String[] args) {

        final ConfigurableApplicationContext ctx =  SpringApplication.run(CatalogoApplication.class, args);
        try {
            final Environment env = ctx.getEnvironment();
            LOGGER.info("\n ********************** \n" + "\tAplicacao {} iniciada com sucesso!\n" + "\tDisponivel nos enderecos:\n"
                            + "\tLocal: http://localhost:{}\n" + "\tExterno: {}\n"
                            + "\tSwagger Url: {}\n"
                            + "\tLocal Swagger Url: http://localhost:{}\n",
                    env.getProperty("spring.application.name"),
                    env.getProperty(SERVER_PORT),
                    env.getProperty(URL_APP),
                    env.getProperty(URL_APP) /*+ SWAGGER_UI_HTML*/,
                    env.getProperty(SERVER_PORT) /*+ SWAGGER_UI_HTML*/);
        } catch (final Exception e) {
            LOGGER.error("Falha ao executar aplicacao: {}", e);
            ctx.close();
        }
	}

}
