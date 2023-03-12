package com.myexam.domain.service.contract.token;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TokenResponseDto {

  private HttpStatus status;

  private String message;

  private String code;

  private String accessToken;

  private long accessTokenExpiresAt;

  private long accessTokenDuration;

  private String refreshToken;

  private long refreshTokenExpiresAt;

  private long refreshTokenDuration;

  private String idToken;

  private String subject;

}
