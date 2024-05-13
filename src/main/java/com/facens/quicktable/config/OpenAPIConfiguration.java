package com.facens.quicktable.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {

   @Bean
   public OpenAPI defineOpenApi() {
       Server server = new Server();
       server.setUrl("https://quicktable.calmwater-4256231c.eastus.azurecontainerapps.io");
       server.setDescription("Producao");

       Contact contatoMarcos = new Contact();
       contatoMarcos.setName("Marcos Duarte");
       contatoMarcos.setEmail("pe.marcos30@gmail.com");

       Info information = new Info()
               .title("QuickTable API")
               .version("1.0")
               .description("API desenvolvida para projeto de engenharia de software.")
               .contact(contatoMarcos);
       return new OpenAPI().info(information).servers(List.of(server));
   }
}