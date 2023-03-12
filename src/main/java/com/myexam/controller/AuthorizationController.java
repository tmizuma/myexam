package com.myexam.controller;

import com.authlete.common.dto.AuthorizationIssueResponse;
import com.myexam.controller.request.authorization.AuthorizationClientRequest;
import com.myexam.controller.request.authorization.AuthorizationIssueClientRequest;
import com.myexam.controller.request.authorization.AuthorizationIssueWithSessionClientRequest;
import com.myexam.domain.service.contract.account.AccountService;
import com.myexam.domain.service.contract.authorization.AuthorizationIssueRequestDto;
import com.myexam.domain.service.contract.authorization.AuthorizationResponseDto;
import com.myexam.domain.service.contract.authorization.AuthorizationService;
import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import com.myexam.module.JwtTokenModule;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/authorization")
public class AuthorizationController {

  private AuthorizationService authorizationService;
  private AccountService accountService;
  private JwtTokenModule jwtToken;

  @Autowired
  public AuthorizationController(
          JwtTokenModule jwtToken,
          AuthorizationService authorizationService,
          AccountService accountService
  ) {
    this.jwtToken = jwtToken;
    this.authorizationService = authorizationService;
    this.accountService = accountService;
  }

  @PostMapping
  public ResponseEntity<AuthorizationResponseDto> authorization(
          @Valid @RequestBody AuthorizationClientRequest req) {
    var response = authorizationService.authorize(req);
    return new ResponseEntity(
            response,
            response.getStatus()
    );
  }

  @PostMapping(value = "/issue", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthorizationIssueResponse> authorization(
          @Valid @RequestBody AuthorizationIssueClientRequest req) {

    var authResult = accountService.authenticate(req.getSub(), req.getPassword());
    if (!authResult.isPresent()) {
      return new ResponseEntity(
              HttpStatus.UNAUTHORIZED
      );
    }
    var authenticationResponse = authResult.get();
    if (authenticationResponse.getChallenge() != AuthenticationNextChallenge.NONE) {
      // ToDo: Implement the patterns of having next challenges.
    }
    var response = authorizationService.issue(AuthorizationIssueRequestDto.of(req.getSub(),
            req.getTicket()));

    // クライアントはこのトークンを用いてセッションが有効かどうかを判断する
    String sessionToken = jwtToken.create(authenticationResponse.getId());
    response.setSession_token(sessionToken);
    return new ResponseEntity(
            response,
            response.getStatus()
    );
  }

  /**
   * Authorizationヘッダーに有効な認証トークン(jwt)が存在している場合に呼び出される
   */
  @PostMapping(value = "/issuewithsession", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthorizationIssueResponse> authorizationWithSession(
          Principal principal,
          @Valid @RequestBody AuthorizationIssueWithSessionClientRequest req) {
    Authentication authentication = (Authentication) principal;
    if (authentication == null) {
      return new ResponseEntity(
              HttpStatus.UNAUTHORIZED
      );
    }
    var response = authorizationService.issue(AuthorizationIssueRequestDto.of(req.getSub(),
            req.getTicket()));
    return new ResponseEntity(
            response,
            response.getStatus()
    );
  }

}