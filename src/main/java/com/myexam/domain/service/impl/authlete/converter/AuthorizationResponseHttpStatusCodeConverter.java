package com.myexam.domain.service.impl.authlete.converter;

import com.authlete.common.dto.AuthorizationResponse;
import org.springframework.http.HttpStatus;

public class AuthorizationResponseHttpStatusCodeConverter {

  public HttpStatus getHttpStatusCode(AuthorizationResponse authorizationResponse) {
    var action = authorizationResponse.getAction();
    switch (action) {
      case INTERNAL_SERVER_ERROR -> {
        return HttpStatus.INTERNAL_SERVER_ERROR;
      }
      case BAD_REQUEST -> {
        return HttpStatus.BAD_REQUEST;
      }
      case LOCATION -> {
        return HttpStatus.FOUND;
      }
      default -> {
        return HttpStatus.OK;
      }
    }
  }
}
