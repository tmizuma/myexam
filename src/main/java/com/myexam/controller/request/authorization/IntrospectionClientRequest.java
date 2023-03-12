package com.myexam.controller.request.authorization;

import com.myexam.controller.request.authlete.AbstractOAuthRequest;
import jakarta.validation.constraints.NotBlank;

public class IntrospectionClientRequest extends AbstractOAuthRequest {

  @NotBlank
  private String token;

  public void setToken(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

}
