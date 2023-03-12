package com.myexam.infrastructure.externalApi.authlete;

import com.authlete.common.api.AuthleteApi;
import com.authlete.common.dto.AuthorizationRequest;
import com.authlete.common.dto.AuthorizationResponse;
import com.myexam.controller.request.authorization.AuthorizationClientRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHandler {

  public AuthorizationResponse handle(AuthleteApi api, AuthorizationClientRequest request) {
    var authorizationRequest = new AuthorizationRequest();
    authorizationRequest.setParameters(request.createOAuthRequestMap());
    return api.authorization(authorizationRequest);
  }
}
