package com.myexam.domain.service.impl.authlete.introspection;

import com.authlete.common.api.AuthleteApiFactory;
import com.myexam.controller.request.authorization.IntrospectionClientRequest;
import com.myexam.domain.service.contract.introspection.IntrospectionResponseDto;
import com.myexam.domain.service.contract.introspection.IntrospectionService;
import com.myexam.domain.service.impl.authlete.converter.IntrospectionResponseHttpStatusCodeConverter;
import com.myexam.infrastructure.externalApi.authlete.IntrospectionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AuthleteIntrospectionService")
public class IntrospectionServiceImpl implements IntrospectionService {

  private final IntrospectionHandler introspectionHandler;

  @Autowired
  public IntrospectionServiceImpl(IntrospectionHandler introspectionHandler) {
    this.introspectionHandler = introspectionHandler;
  }

  public IntrospectionResponseDto validate(IntrospectionClientRequest req) {
    var response = introspectionHandler.handle(AuthleteApiFactory.getDefaultApi(), req);

    // dtoへの詰め替え
    var dto = new IntrospectionResponseDto();
    BeanUtils.copyProperties(response, dto);
    dto.setMessage(response.getResultMessage());
    var converter = new IntrospectionResponseHttpStatusCodeConverter();
    dto.setStatus(converter.getHttpStatusCode(response));

    return dto;
  }

}