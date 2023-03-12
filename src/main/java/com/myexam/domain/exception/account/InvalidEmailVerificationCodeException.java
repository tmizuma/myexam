package com.myexam.domain.exception.account;

public class InvalidEmailVerificationCodeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public static final String messageKey = "exception.invalid.email.verification.code";

  public InvalidEmailVerificationCodeException(String message) {
    super(message);
  }
}
