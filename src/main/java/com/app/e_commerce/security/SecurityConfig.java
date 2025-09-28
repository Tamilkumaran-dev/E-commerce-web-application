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
            http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/profile/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"/profile/**").authenticated()
                        .anyRequest().authenticated()
                ).addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
