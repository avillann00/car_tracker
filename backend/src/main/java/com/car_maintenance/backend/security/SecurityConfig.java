package com.car_maintenance.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig{
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session ->
          session.sessionCreationPolicy(STATELESS)
      )
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/auth/register", "/auth/login").permitAll()
          .requestMatchers("/users/**").hasRole("ADMIN")
          .anyRequest().authenticated()
      );

    return http.build();
  }
}
