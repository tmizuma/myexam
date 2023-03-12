package com.myexam.domain.exception.account;

public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public static final String messageKey = "exception.user.not.found";

  public UserNotFoundException(String message) {
    super(message);
  }

}
