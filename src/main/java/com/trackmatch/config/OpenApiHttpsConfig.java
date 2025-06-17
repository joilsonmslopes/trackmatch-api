package com.trackmatch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiHttpsConfig {

    @Bean
    OpenAPI customOpenApi(
            @Value("${swagger.server-url}") String serverUrl) {

        return new OpenAPI()
                .addServersItem(new Server().url(serverUrl));
    }
}
