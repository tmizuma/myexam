package com.myexam.controller;

import com.authlete.common.dto.AuthorizationResponse;
import com.myexam.controller.request.authorization.TokenClientRequest;
import com.myexam.domain.service.contract.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/token")
public class TokenController {

  TokenService service;

  public TokenController(@Autowired TokenService service) {
    this.service = service;
  }

  @PostMapping()
  public ResponseEntity<AuthorizationResponse> token(
          @Valid @RequestBody TokenClientRequest req) {

    var response = service.getToken(req);
    return new ResponseEntity(
            response,
            response.getStatus()
    );
  }
}