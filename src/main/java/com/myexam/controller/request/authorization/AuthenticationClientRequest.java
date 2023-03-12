package com.myexam.controller.request.authorization;

import com.myexam.controller.request.authlete.AbstractOAuthRequest;
import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import lombok.Data;

@Data
public class AuthenticationClientRequest extends AbstractOAuthRequest {

  private String sub;

  private String password;

  private AuthenticationNextChallenge next_challenge;

}
