package com.myexam.controller.response;

import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import lombok.Data;

@Data
public class AuthenticationResponse {

  public static AuthenticationResponse of(boolean authStatus, String sub,
          AuthenticationNextChallenge challenge) {
    var response = new AuthenticationResponse();
    response.setSub(sub);
    response.challenge = challenge;
    return response;
  }

  private AuthenticationNextChallenge challenge;

  private String sub;

}
