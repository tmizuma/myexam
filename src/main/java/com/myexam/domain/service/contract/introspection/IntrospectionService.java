package com.myexam.domain.service.contract.introspection;

import com.myexam.controller.request.authorization.IntrospectionClientRequest;

public interface IntrospectionService {

  IntrospectionResponseDto validate(IntrospectionClientRequest req);
}
