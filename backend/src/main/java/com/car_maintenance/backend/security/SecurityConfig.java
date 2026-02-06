package com.car_maintenance.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.car_maintenance.backend.service.JwtService;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig{
  private final JwtService jwtService;

  public SecurityConfig(JwtService jwtService){
    this.jwtService = jwtService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session ->
          session.sessionCreationPolicy(STATELESS)
      )
      .addFilterBefore(new JwtAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/auth/register", "/auth/login").permitAll()
          .requestMatchers("/users/**").hasRole("ADMIN")
          .anyRequest().authenticated()
      );

    return http.build();
  }
}
