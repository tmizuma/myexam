package com.myexam.domain.service.contract.authorization;

import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthorizationIssueResponseDto {

  private HttpStatus status;

  private String message;

  private String responseContent;

  private AuthenticationNextChallenge challenge;

  private String session_token;
}
