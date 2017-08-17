package com.example.project.config;


import static com.google.common.collect.Lists.newArrayList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.ImmutableSet;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Clase de configuración de la aplicacion
 * @author cmata
 *
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public Docket ejemploApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.project"))
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder()
                .code(500)
                .message("Internal error").build()))
            .produces(ImmutableSet.of("application/json"))
            .tags(new Tag("Aplicacion de Prueba", "Aplicación para repasar conceptos"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Ejemplos con Spring Boot - REST API")
            .version("1.0")
            .build();
    }
}
