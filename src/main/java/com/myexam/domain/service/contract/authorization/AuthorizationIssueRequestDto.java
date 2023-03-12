package com.myexam.domain.service.contract.authorization;

import lombok.Data;

@Data
public class AuthorizationIssueRequestDto {

  public static AuthorizationIssueRequestDto of(String sub, String ticket) {
    var authorizationIssueRequestDto = new AuthorizationIssueRequestDto();
    authorizationIssueRequestDto.setSub(sub);
    authorizationIssueRequestDto.setTicket(ticket);
    return authorizationIssueRequestDto;
  }

  private String sub;
  private String ticket;

}
