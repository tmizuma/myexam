package com.myexam.domain.exception.account;

public class AlreadyUserExistsException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public static final String messageKey = "exception.already.user.exists";

  public AlreadyUserExistsException(String message) {
    super(message);
  }
}
