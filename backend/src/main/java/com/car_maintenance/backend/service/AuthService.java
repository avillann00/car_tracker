package com.car_maintenance.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.car_maintenance.backend.dto.LoginRequest;
import com.car_maintenance.backend.dto.LoginResponse;
import com.car_maintenance.backend.dto.RegisterResponse;
import com.car_maintenance.backend.dto.RegisterRequest;
import com.car_maintenance.backend.model.User;
import com.car_maintenance.backend.repository.UserRepository;

@Service
public class AuthService{
  private final PasswordEncoder encoder;
  private final UserRepository repo;
  private final JwtService jwtService;

  public AuthService(PasswordEncoder encoder, UserRepository repo, JwtService jwtService){
    this.encoder = encoder;
    this.repo = repo;
    this.jwtService = jwtService;
  }

  public LoginResponse login(LoginRequest request){
    User user = repo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email"));

    if(!encoder.matches(request.getPassword(), user.getPassword())){
      throw new RuntimeException("Invalid password");
    }

    String token = jwtService.generateToken(user);
    return new LoginResponse(token);
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
