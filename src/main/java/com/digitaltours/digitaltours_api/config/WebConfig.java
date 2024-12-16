package com.digitaltours.digitaltours_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir CORS en todas las rutas
                .allowedOrigins("*") // Cambia a tu dominio permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin",
                        "Content-Disposition", "Content-Length", "Method", "X-Forwarded-For", "X-Real-IP",
                        "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
                        "Access-Control-Allow-Methods")
                .exposedHeaders("Authorization", "Content-Disposition", "X-Get-Header")
                .allowCredentials(false); // Habilita credenciales si es necesario
    }
}