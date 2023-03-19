package com.myexam.exception;

public class AuthenticationException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  private String message;

  public AuthenticationException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
