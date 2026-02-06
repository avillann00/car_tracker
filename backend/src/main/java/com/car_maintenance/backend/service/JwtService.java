package com.car_maintenance.backend.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.car_maintenance.backend.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService{
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String generateToken(User user){
    return Jwts.builder()
      .setSubject(user.getEmail())
      .claim("userId", user.getId())
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 86400000))
      .signWith(key)
      .compact();
  }

  public Claims parseToken(String token){
    return Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }
}
