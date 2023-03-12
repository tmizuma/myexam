package com.myexam.domain.service.impl.account.domain.object;

import com.myexam.domain.exception.account.InvalidEmailVerificationCodeException;

public class VerificationCode {

  private String code;

  public static final int VERIFICATION_CODE_LENGTH = 4;

  public static VerificationCode of(String code) {
    return new VerificationCode(code);
  }

  private VerificationCode(String code) {
    if (code == null) {
      throw new InvalidEmailVerificationCodeException(null);
    }

    if (code.length() != VERIFICATION_CODE_LENGTH || isNumeric(code)) {
      throw new InvalidEmailVerificationCodeException(code);
    }

    this.code = code;
  }

  private boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    return str.matches("\\d+");
  }

  public String getValue() {
    return code;
  }

}
