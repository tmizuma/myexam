package com.myexam.domain.service.impl.authlete.converter;

import com.authlete.common.dto.AuthorizationIssueResponse;
import org.springframework.http.HttpStatus;

public class AuthorizationIssueResponseHttpStatusCodeConverter {

  public HttpStatus getHttpStatusCode(AuthorizationIssueResponse authorizationIssueResponse) {
    var action = authorizationIssueResponse.getAction();
    switch (action) {
      case INTERNAL_SERVER_ERROR -> {
        return HttpStatus.INTERNAL_SERVER_ERROR;
      }
      case BAD_REQUEST -> {
        return HttpStatus.BAD_REQUEST;
      }
      default -> {
        return HttpStatus.OK;
      }
    }
  }
}
