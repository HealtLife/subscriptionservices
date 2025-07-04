package com.acme.nutrimove.subscriptionservices.backend.subscriptions.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // Define el patrón de la ruta
                .allowedOrigins("http://localhost:4200") // Origen permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Si necesitas enviar cookies con las solicitudes
    }
}
