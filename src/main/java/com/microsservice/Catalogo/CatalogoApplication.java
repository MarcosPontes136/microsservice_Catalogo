package com.microsservice.Catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CatalogoApplication {
	
    private static final String URL_APP = "url.aplicacao";
    private static final String SERVER_PORT = "server.port";
    private static final String SWAGGER_UI_HTML = "/swagger-ui/index.html";
    
	public static void main(String[] args) {

        final ConfigurableApplicationContext ctx =  SpringApplication.run(CatalogoApplication.class, args);
        try {
            final Environment env = ctx.getEnvironment();
            log.info("\n ********************** \n" + "\tAplicacao {} iniciada com sucesso!\n" + "\tDisponivel nos enderecos:\n"
                            + "\tLocal: http://localhost:{}\n" 
                            + "\tExterno: {}\n"
                            + "\tSwagger Url: {}\n"
                            + "\tLocal Swagger Url: http://localhost:{}\n",
                    env.getProperty("spring.application.name"),
                    env.getProperty(SERVER_PORT),
                    env.getProperty(URL_APP),
                    env.getProperty(URL_APP) + SWAGGER_UI_HTML,
                    env.getProperty(SERVER_PORT) + SWAGGER_UI_HTML);
        } catch (final Exception e) {
        	log.error("Falha ao executar aplicacao: {}", e);
            ctx.close();
        }
	}

}
