package com.myexam.exception;

public class UserNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  private String message;

  public UserNotFoundException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
