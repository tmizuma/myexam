package com.myexam.exception;

public class RecipeNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  private String message;

  public RecipeNotFoundException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
