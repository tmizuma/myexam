package com.myexam.domain.service.impl.account.domain.object;

public enum AuthenticationNextChallenge {
  EMAIL_VERIFICATION,
  SOFTWARE_TOKEN_MFA,
  PASSWORD_FORCE_CHANGE,
  NONE
}