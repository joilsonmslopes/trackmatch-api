package com.trackmatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final List<String> allowedOrigins;

    public CorsConfig(
            @Value("#{'${cors.allowed-origins}'.split('\\s*,\\s*')}")  // remove espaços
            List<String> allowedOrigins) {

        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins.toArray(String[]::new))
                .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
                .allowedHeaders("*")            // evita erro quando front envia Authorization, etc.
                .exposedHeaders("Location")     // opcional: headers que o front pode ler
                .allowCredentials(true)
                .maxAge(3600);                  // cache do pre-flight
    }
}
