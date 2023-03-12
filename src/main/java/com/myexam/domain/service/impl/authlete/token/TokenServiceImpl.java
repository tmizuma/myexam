package com.myexam.domain.service.impl.authlete.token;

import com.authlete.common.api.AuthleteApiFactory;
import com.myexam.controller.request.authorization.TokenClientRequest;
import com.myexam.domain.service.contract.token.TokenResponseDto;
import com.myexam.domain.service.contract.token.TokenService;
import com.myexam.domain.service.impl.authlete.converter.TokenResponseHttpStatusCodeConverter;
import com.myexam.infrastructure.externalApi.authlete.TokenHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AuthleteTokenService")
public class TokenServiceImpl implements TokenService {

  private final TokenHandler tokenHandler;

  @Autowired
  public TokenServiceImpl(TokenHandler tokenHandler) {
    this.tokenHandler = tokenHandler;
  }

  public TokenResponseDto getToken(TokenClientRequest req) {
    var response = tokenHandler.handle(AuthleteApiFactory.getDefaultApi(), req);

    // dtoへの詰め替え
    var dto = new TokenResponseDto();
    BeanUtils.copyProperties(response, dto);
    dto.setMessage(response.getResultMessage());
    var converter = new TokenResponseHttpStatusCodeConverter();
    dto.setStatus(converter.getHttpStatusCode(response));

    return dto;
  }

}