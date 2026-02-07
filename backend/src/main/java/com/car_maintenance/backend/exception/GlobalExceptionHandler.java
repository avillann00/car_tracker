package com.car_maintenance.backend.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex){
    return ResponseEntity.status(404).body(
      Map.of("error", ex.getMessage())
    );
  }

  @ExceptionHandler(EmailAlreadyRegisteredException.class) 
  public ResponseEntity<Map<String, String>> handleEmailExists(EmailAlreadyRegisteredException ex){
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(Map.of("message", ex.getMessage()));
  }
}
