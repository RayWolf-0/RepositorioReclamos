package cl.duoc.demoreclamos.Config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class swaggerconfig{
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Microservicio de reclamos")
            .version("1.0")
            .description("Documentación de la API de reclamos")
        );
    }

}
