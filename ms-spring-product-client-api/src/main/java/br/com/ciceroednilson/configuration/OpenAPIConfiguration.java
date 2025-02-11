package br.com.ciceroednilson.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Client API")
                        .version("1.0")
                        .description("Lab to learn"))
                .externalDocs(new ExternalDocumentation()
                        .description("Github Cícero")
                        .url("https://github.com/ciceroednilson"));
    }
}
