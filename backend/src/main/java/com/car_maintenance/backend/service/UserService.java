package com.car_maintenance.backend.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.car_maintenance.backend.dto.UserCreateRequest;
import com.car_maintenance.backend.dto.UserResponse;
import com.car_maintenance.backend.model.User;
import com.car_maintenance.backend.repository.UserRepository;

@Service
public class UserService{
  private final UserRepository repo;
  private final PasswordEncoder encoder;

  public UserService(UserRepository repo, PasswordEncoder encoder){
    this.repo = repo;
    this.encoder = encoder;
  }

  public UserResponse createUser(UserCreateRequest request){
    User user = new User();
    user.setEmail(request.getEmail());
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setPassword(this.encoder.encode(request.getPassword()));

    User saved = this.repo.save(user);

    return toResponse(saved);
  }

  public List<UserResponse> getAllUsers(){
    return this.repo.findAll().stream().map(this::toResponse).toList();
  }

  private UserResponse toResponse(User user){
    UserResponse dto = new UserResponse();

    dto.setId(user.getId());
    dto.setEmail(user.getEmail());
    dto.setFirstName(user.getFirstName());
    dto.setLastName(user.getLastName());

    return dto;
  }
}
