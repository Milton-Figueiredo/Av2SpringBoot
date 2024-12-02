package com.prova.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("prova")
                .pathsToMatch("/**")
                .packagesToScan("com.prova.resources")
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("Concessionária")
                        .description("Concessionária APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Concessionária")
                                .url("http://www.avaliacaoconcessionária.com.br")
                                .email("concessionária@gmail.com")));
    }
}
