package com.myexam.domain.service.contract.token;

import com.myexam.controller.request.authorization.TokenClientRequest;

public interface TokenService {

  TokenResponseDto getToken(TokenClientRequest req);
}
