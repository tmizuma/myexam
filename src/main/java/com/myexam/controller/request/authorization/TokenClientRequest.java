package com.myexam.controller.request.authorization;

import com.myexam.controller.request.authlete.AbstractOAuthRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenClientRequest extends AbstractOAuthRequest {

  private String code;

  private String redirect_uri;

  @NotBlank
  private String grant_type; // e.x) authorization_code or refresh_token

  @NotBlank
  private String client_id;

  private String code_verifier;

  private String refresh_token;

}
