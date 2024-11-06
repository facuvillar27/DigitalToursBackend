// package com.digitaltours.digitaltours_api.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF si no es necesario para pruebas
//                 .authorizeHttpRequests(auth -> auth
//                         .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**",
//                                 "/**")
//                         .permitAll() // Permite todas las solicitudes sin autenticación
//                 );

//         return http.build();
//     }
// }