package com.myexam.exception;

public class RecipeDuplicatedException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  private String message;

  public RecipeDuplicatedException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
