package com.myexam.infrastructure.repositoryImpl.email;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean //annotation for DynamoDb mappable entity.
public class EmailVerificationEntity {

  private String email;
  private long ttl;
  private String verificationCode;
  private String nonce;

  @DynamoDbPartitionKey
  public String getEmail() {
    return email;
  }

  public long getTtl() {
    return ttl;
  }

  public String getVerificationCode() {
    return verificationCode;
  }

  public String getNonce() {
    return nonce;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setTtl(long ttl) {
    this.ttl = ttl;
  }

  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }

  public void setNonce(String nonce) {
    this.nonce = nonce;
  }
}
