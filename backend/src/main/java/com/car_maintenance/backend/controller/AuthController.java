package com.car_maintenance.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.car_maintenance.backend.dto.LoginRequest;
import com.car_maintenance.backend.dto.LoginResponse;
import com.car_maintenance.backend.dto.RegisterResponse;
import com.car_maintenance.backend.dto.RegisterRequest;
import com.car_maintenance.backend.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController{
  private final AuthService service; 

  public AuthController(AuthService service){
    this.service = service;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response){
    LoginResponse loginResponse = this.service.login(request);

    Cookie cookie = new Cookie("access_token", loginResponse.getToken());
    cookie.setHttpOnly(true);
    cookie.setSecure(false);
    cookie.setPath("/");
    cookie.setMaxAge(60 * 60);
    cookie.setAttribute("SameSite", "Lax");

    response.addCookie(cookie);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/register")
  public RegisterResponse register(@Valid @RequestBody RegisterRequest request){
    return this.service.register(request);
  }
}
