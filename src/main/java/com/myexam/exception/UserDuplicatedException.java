package com.myexam.exception;

public class UserDuplicatedException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  private String message;

  private String cause;

  final String DIVIDER = "/";

  public UserDuplicatedException(String message, String cause) {
    this.message = message;
    this.cause = cause;
  }

  @Override
  public String getMessage() {

    return message + DIVIDER + this.cause;
  }

}
