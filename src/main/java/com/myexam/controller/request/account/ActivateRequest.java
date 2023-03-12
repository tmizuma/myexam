package com.myexam.controller.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActivateRequest {

  @NotNull
  private String id;
  @NotNull
  private String nonce;
  @NotNull
  private String verificationCode;

}
