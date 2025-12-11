package io.github.Erissonteixeira.api_conta_bancaria.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Conta Bancária")
                        .description("Documentação da API Conta Bancária com Swagger / OpenAPI 3")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Erisson Teixeira")
                                .email("seu-email@exemplo.com")
                        )
                );
    }
}