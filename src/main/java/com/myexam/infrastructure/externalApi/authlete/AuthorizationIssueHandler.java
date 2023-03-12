package com.myexam.infrastructure.externalApi.authlete;

import com.authlete.common.api.AuthleteApi;
import com.authlete.common.dto.AuthorizationIssueRequest;
import com.authlete.common.dto.AuthorizationIssueResponse;
import com.myexam.domain.service.contract.authorization.AuthorizationIssueRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationIssueHandler {

  public AuthorizationIssueResponse handle(AuthleteApi api,
          AuthorizationIssueRequestDto request) {
    var authorizationIssueRequest = new AuthorizationIssueRequest();
    authorizationIssueRequest.setTicket(request.getTicket());
    authorizationIssueRequest.setSubject(request.getSub());
    return api.authorizationIssue(authorizationIssueRequest);
  }

}
