package com.car_maintenance.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.car_maintenance.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
