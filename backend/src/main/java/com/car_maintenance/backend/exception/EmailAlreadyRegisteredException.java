package com.car_maintenance.backend.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{
  public EmailAlreadyRegisteredException(String message){
    super(message);
  }
}
