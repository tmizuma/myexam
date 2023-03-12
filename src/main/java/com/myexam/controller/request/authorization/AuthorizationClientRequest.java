package com.myexam.controller.request.authorization;

import com.myexam.controller.request.authlete.AbstractOAuthRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
/**
 * also see: https://openid-foundation-japan.github.io/rfc6749.ja.html#code-authz-req
 */
public class AuthorizationClientRequest extends AbstractOAuthRequest {

  @NotBlank
  private String response_type;

  @NotBlank
  private String redirect_uri;

  @NotBlank
  private String code_challenge;

  private String state; // Optional but recommended

  private String scope; // Optional

  private String client_id;

  @NotBlank
  private String code_challenge_method; // e.x) S256

}
