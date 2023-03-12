package com.myexam.controller.request.authorization;

import com.myexam.controller.request.authlete.AbstractOAuthRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorizationIssueWithSessionClientRequest extends AbstractOAuthRequest {

  @NotNull
  private String ticket;
  @NotNull
  private String sub;

}
