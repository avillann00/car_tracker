package com.car_maintenance.backend.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserCreateRequest{
  @Email
  @NotBlank
  private String email;

  @NotBlank 
  @Size(min=2)
  private String firstName;

  @NotBlank 
  @Size(min=2)
  private String lastName;

  @NotBlank 
  @Size(min=2)
  private String password;
}
