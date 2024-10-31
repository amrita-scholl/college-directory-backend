package com.example.collegedirectorybackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Apply CORS to /api/** paths
                .allowedOrigins("http://localhost:4200")  // Allow requests from localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow the specified methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials such as cookies

    }
}

