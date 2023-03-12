package com.myexam.infrastructure.externalApi.authlete;

import com.authlete.common.api.AuthleteApi;
import com.authlete.common.dto.IntrospectionRequest;
import com.authlete.common.dto.IntrospectionResponse;
import com.myexam.controller.request.authorization.IntrospectionClientRequest;
import org.springframework.stereotype.Component;

@Component
public class IntrospectionHandler {

  public IntrospectionResponse handle(AuthleteApi api,
          IntrospectionClientRequest request) {
    var introspectionRequest = new IntrospectionRequest();
    introspectionRequest.setToken(request.getToken());
    return api.introspection(introspectionRequest);
  }

}
