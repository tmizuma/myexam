package com.myexam.domain.service.contract.introspection;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class IntrospectionResponseDto {

  private HttpStatus status;

  private String message;

  private String subject;

  private long expiresAt;

  private boolean existent;

}
