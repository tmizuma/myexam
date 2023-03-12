package com.myexam.domain.service.impl.authlete.authorization;

import com.authlete.common.api.AuthleteApiFactory;
import com.myexam.controller.request.authorization.AuthorizationClientRequest;
import com.myexam.domain.repositories.user.UserRepository;
import com.myexam.domain.service.contract.authorization.AuthorizationIssueRequestDto;
import com.myexam.domain.service.contract.authorization.AuthorizationIssueResponseDto;
import com.myexam.domain.service.contract.authorization.AuthorizationResponseDto;
import com.myexam.domain.service.contract.authorization.AuthorizationService;
import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import com.myexam.domain.service.impl.authlete.converter.AuthorizationIssueResponseHttpStatusCodeConverter;
import com.myexam.domain.service.impl.authlete.converter.AuthorizationResponseHttpStatusCodeConverter;
import com.myexam.infrastructure.externalApi.authlete.AuthorizationHandler;
import com.myexam.infrastructure.externalApi.authlete.AuthorizationIssueHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AuthleteAuthorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

  private final UserRepository repo;
  private final AuthorizationHandler authorizationHandler;
  private final AuthorizationIssueHandler authorizationIssueHandler;

  @Autowired
  public AuthorizationServiceImpl(
          @Qualifier("UserDynamoDBRepository")
          UserRepository repo,
          AuthorizationHandler authorizationHandler,
          AuthorizationIssueHandler authorizationIssueHandler
  ) {
    this.repo = repo;
    this.authorizationHandler = authorizationHandler;
    this.authorizationIssueHandler = authorizationIssueHandler;
  }

  public AuthorizationResponseDto authorize(AuthorizationClientRequest req) {
    var response = authorizationHandler.handle(AuthleteApiFactory.getDefaultApi(), req);
    var dto = new AuthorizationResponseDto();

    // dtoへのデータ詰め替え
    BeanUtils.copyProperties(response, dto);
    dto.setMessage(response.getResultMessage());
    var responseCodeConverter = new AuthorizationResponseHttpStatusCodeConverter();
    dto.setStatus(responseCodeConverter.getHttpStatusCode(response));
    return dto;
  }

  public AuthorizationIssueResponseDto issue(AuthorizationIssueRequestDto req) {
    var dto = new AuthorizationIssueResponseDto();
    var response = authorizationIssueHandler.handle(AuthleteApiFactory.getDefaultApi(), req);
    // dtoへのデータ詰め替え
    BeanUtils.copyProperties(response, dto);
    dto.setMessage(response.getResultMessage());
    var converter = new AuthorizationIssueResponseHttpStatusCodeConverter();
    dto.setStatus(converter.getHttpStatusCode(response));
    dto.setChallenge(AuthenticationNextChallenge.NONE);
    return dto;
  }
}