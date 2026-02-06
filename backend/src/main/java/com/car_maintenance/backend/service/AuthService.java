package com.car_maintenance.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.car_maintenance.backend.dto.RegisterResponse;
import com.car_maintenance.backend.dto.RegisterRequest;
import com.car_maintenance.backend.model.User;
import com.car_maintenance.backend.repository.UserRepository;

@Service
public class AuthService{
  private final PasswordEncoder encoder;
  private final UserRepository repo;

  public AuthService(PasswordEncoder encoder, UserRepository repo){
    this.encoder = encoder;
    this.repo = repo;
  }

  public RegisterResponse register(RegisterRequest request){
    User user = new User();

    user.setEmail(request.getEmail());
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setPassword(this.encoder.encode(request.getPassword()));

    User saved = this.repo.save(user);

    return toResponse(user);
  }

  private RegisterResponse toResponse(User user){
    RegisterResponse dto = new RegisterResponse();

    dto.setId(user.getId());
    dto.setEmail(user.getEmail());
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());

    return dto;
  }
}
