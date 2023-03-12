package com.myexam.domain.service.impl.authlete.converter;

import com.authlete.common.dto.TokenResponse;
import org.springframework.http.HttpStatus;

public class TokenResponseHttpStatusCodeConverter {

  public HttpStatus getHttpStatusCode(TokenResponse tokenResponse) {
    var action = tokenResponse.getAction();
    switch (action) {
      case INTERNAL_SERVER_ERROR -> {
        return HttpStatus.INTERNAL_SERVER_ERROR;
      }
      case BAD_REQUEST -> {
        return HttpStatus.BAD_REQUEST;
      }
      case INVALID_CLIENT -> {
        return HttpStatus.UNAUTHORIZED;
      }
      default -> {
        return HttpStatus.OK;
      }
    }
  }
}
