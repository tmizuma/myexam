package com.myexam.domain.service.impl.account.domain.object;

public class VerificationNonce {

  private String nonce;

  public static VerificationNonce of(String nonce) {
    return new VerificationNonce(nonce);
  }

  private VerificationNonce(String nonce) {
    this.nonce = nonce;
  }

  public String getValue() {
    return nonce;
  }
}
