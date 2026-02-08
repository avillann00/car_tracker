package com.car_maintenance.backend.security;

import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.car_maintenance.backend.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter{
  private final JwtService jwtService;

  public JwtAuthFilter(JwtService jwtService){
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException{
    String token = extractTokenFromCookie(request);

    if(token == null){
      chain.doFilter(request, response);
      return;
    }

    Claims claims = jwtService.parseToken(token);

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, List.of());

    SecurityContextHolder.getContext().setAuthentication(auth);
    chain.doFilter(request, response);
  }

  private String extractTokenFromCookie(HttpServletRequest request){
    if(request.getCookies() == null){
      return null;
    }

    for(Cookie cookie : request.getCookies()){
      if("access_token".equals(cookie.getName())){
        return cookie.getValue();
      }
    }
    return null;
  }
}
