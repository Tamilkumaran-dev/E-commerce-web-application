package com.app.e_commerce.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtFilterChain jwtFilterChain) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth","/auth/**").permitAll()   // Allow all GET requests
                        .anyRequest().authenticated()                         // All other requests (POST, PUT, DELETE) require authentication
                ).addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
