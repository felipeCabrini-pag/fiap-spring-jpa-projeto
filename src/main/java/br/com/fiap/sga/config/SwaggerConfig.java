package br.com.fiap.sga.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Aplicacao Sistema academico",
                version = "v1",
                description = " API do sistema de gestão academêmica",
                contact = @Contact(name = "univsersidade agil", email = "api@universidade.edu.br"),
                license = @License(name = "MIT")
        ),
        servers = {@Server(url = "http://localhost:8080",description = "LOCAL"),
                @Server(url = "http://sgi.ambiente.qa.intranet",description = "QA"),},
        tags = {
                @Tag(name = "Alunos",description = "Operacoes relacionadas a aluno")
        }
)
public class SwaggerConfig {
}
