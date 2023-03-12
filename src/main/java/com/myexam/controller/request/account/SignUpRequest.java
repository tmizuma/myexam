package com.myexam.controller.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {

  @NotNull
  // @Email WNではemailが事実上のIDになるが、将来にわたってこの仕様が正しいかわからないため@Emailチェックは行わない
  private String id;
  @NotNull
  private String password;

}
