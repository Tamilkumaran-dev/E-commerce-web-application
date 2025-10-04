package com.app.e_commerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://e-commerce-application-frontend-six.vercel.app") // exact frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // include OPTIONS
                        .allowedHeaders("*")
                        .allowCredentials(true); // allow cookies
            }
        };
    }
}