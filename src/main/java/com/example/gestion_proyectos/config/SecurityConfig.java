package com.example.gestion_proyectos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desactiva CSRF (útil para Postman)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/proyectos/**", "/api/tareas/**").permitAll()  // Permitir acceso sin autenticación
                .anyRequest().authenticated()
            )
            .formLogin(login -> login.disable())  // Desactiva el formulario de login por defecto
            .httpBasic(httpBasic -> httpBasic.disable());  // Desactiva autenticación básica

        return http.build();
    }
}
