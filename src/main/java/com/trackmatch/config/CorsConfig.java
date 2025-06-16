package com.trackmatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration()
public class CorsConfig implements WebMvcConfigurer {
    @Value("#{'${cors.allowed-origins}'.split(',')}")
    private final List<String> allowedOrigins;

    public CorsConfig(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedOrigins(allowedOrigins.toArray(String[]::new))
                .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
                .allowCredentials(true);
    }
}
