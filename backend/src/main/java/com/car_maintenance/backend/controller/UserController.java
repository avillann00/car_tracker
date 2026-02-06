package com.car_maintenance.backend.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.car_maintenance.backend.dto.UserCreateRequest;
import com.car_maintenance.backend.dto.UserResponse;
import com.car_maintenance.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController{
  private final UserService service;

  public UserController(UserService service){
    this.service = service;
  }

  @GetMapping
  public List<UserResponse> getAllUsers(){
    return this.service.getAllUsers();
  }

  @PostMapping
  public UserResponse createUser(@Valid @RequestBody UserCreateRequest request){
    return this.service.createUser(request);
  }
}
