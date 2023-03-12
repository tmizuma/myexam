package com.myexam.controller;

import com.authlete.common.dto.IntrospectionResponse;
import com.myexam.controller.request.authorization.IntrospectionClientRequest;
import com.myexam.domain.service.contract.introspection.IntrospectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/introspection")
public class IntrospectionController {

  IntrospectionService service;

  public IntrospectionController(@Autowired IntrospectionService service) {
    this.service = service;
  }

  @PostMapping()
  public ResponseEntity<IntrospectionResponse> token(
          @Valid @RequestBody IntrospectionClientRequest req) {

    var response = service.validate(req);
    return new ResponseEntity(
            response,
            response.getStatus()
    );
  }
}