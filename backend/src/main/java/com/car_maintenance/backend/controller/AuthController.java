package com.car_maintenance.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_maintenance.backend.dto.RegisterResponse;
import com.car_maintenance.backend.dto.RegisterRequest;
import com.car_maintenance.backend.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController{
  private final AuthService service; 

  public AuthController(AuthService service){
    this.service = service;
  }


  @PostMapping("/register")
  public RegisterResponse register(@Valid @RequestBody RegisterRequest request){
    return this.service.register(request);
  }
}
