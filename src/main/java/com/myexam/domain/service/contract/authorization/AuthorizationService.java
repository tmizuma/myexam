package com.myexam.domain.service.contract.authorization;

import com.myexam.controller.request.authorization.AuthorizationClientRequest;

public interface AuthorizationService {

  AuthorizationResponseDto authorize(AuthorizationClientRequest req);

  AuthorizationIssueResponseDto issue(AuthorizationIssueRequestDto req);

}
