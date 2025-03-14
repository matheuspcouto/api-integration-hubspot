package br.com.api.integration.hubspot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(createApiInfo());
    }

    private Info createApiInfo() {
        return new Info()
                .title("API Integration HubSpot")
                .version("v1")
                .description("Esta API foi criada para integrar com o HubSpot. " +
                        "Você pode realizar autenticação do tipo OAuth2.0 e obter os tokens de acesso e atualização. " +
                        "Com esses tokens, você pode realizar operações de contatos, como listar e criar contatos no CRM da Hubspot.")
                .contact(createContact());
    }

    private Contact createContact() {
        return new Contact()
                .name("Matheus Pimentel Do Couto")
                .url("https://matheuspcouto.github.io")
                .email("matheuspcouto70@gmail.com");
    }
}
