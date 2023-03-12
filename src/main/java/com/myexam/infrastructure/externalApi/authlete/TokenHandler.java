package com.myexam.infrastructure.externalApi.authlete;

import com.authlete.common.api.AuthleteApi;
import com.authlete.common.dto.TokenRequest;
import com.authlete.common.dto.TokenResponse;
import com.myexam.controller.request.authorization.TokenClientRequest;
import org.springframework.stereotype.Component;

@Component
public class TokenHandler {

  public TokenResponse handle(AuthleteApi api,
          TokenClientRequest request) {
    var tokenRequest = new TokenRequest();
    tokenRequest.setParameters(request.createOAuthRequestMap());
    return api.token(tokenRequest);
  }
}
