package com.myexam.domain.service.impl.authlete.converter;

import com.authlete.common.dto.IntrospectionResponse;
import org.springframework.http.HttpStatus;

public class IntrospectionResponseHttpStatusCodeConverter {

  public HttpStatus getHttpStatusCode(IntrospectionResponse introspectionResponse) {
    var action = introspectionResponse.getAction();
    switch (action) {
      case INTERNAL_SERVER_ERROR -> {
        return HttpStatus.INTERNAL_SERVER_ERROR;
      }
      case BAD_REQUEST -> {
        return HttpStatus.BAD_REQUEST;
      }
      case FORBIDDEN -> {
        return HttpStatus.FORBIDDEN;
      }
      case UNAUTHORIZED -> {
        return HttpStatus.UNAUTHORIZED;
      }
      default -> {
        return HttpStatus.OK;
      }
    }
  }
}
