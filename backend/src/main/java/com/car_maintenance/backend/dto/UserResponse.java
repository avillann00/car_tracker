package com.car_maintenance.backend.dto;

import lombok.Data;

@Data
public class UserResponse{
  private Long id;
  private String email;
  private String firstName;
  private String lastName;
}
