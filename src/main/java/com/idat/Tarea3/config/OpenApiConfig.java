package com.idat.Tarea3.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Gestión de Personas")
                        .version("1.0.0")
                        .description("API REST completa para gestión de personas con relaciones JPA. " +
                                "Incluye operaciones CRUD, manejo de documentos, direcciones y mascotas. " +
                                "Desarrollada como parte de EC1 - Desarrollo de Servicios Web 2 - IDAT")
                        .contact(new Contact()
                                .name("IDAT - Desarrollo de Servicios Web 2")
                                .email("contacto@idat.edu.pe"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo")
                ))
                .components(new Components().addSecuritySchemes("basicAuth",new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))).addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }
}