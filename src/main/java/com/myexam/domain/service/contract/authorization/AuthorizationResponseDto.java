package com.myexam.domain.service.contract.authorization;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthorizationResponseDto {

  private HttpStatus status;

  private String message;

  private String ticket;

}
