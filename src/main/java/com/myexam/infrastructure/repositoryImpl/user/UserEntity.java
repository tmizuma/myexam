package com.myexam.infrastructure.repositoryImpl.user;

import com.myexam.domain.service.impl.account.UserStatus;
import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean //annotation for DynamoDb mappable entity.
public class UserEntity {

  private String id;
  private String hashedPassword;
  private AuthenticationNextChallenge challenge;

  private UserStatus userStatus;

  @DynamoDbPartitionKey
  public String getId() {
    return id;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public AuthenticationNextChallenge getNextChallenge() {
    return challenge;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public void setChallenge(AuthenticationNextChallenge challenge) {
    this.challenge = challenge;
  }

  public void setUserStatus(UserStatus userStatus) {
    this.userStatus = userStatus;
  }

  public UserStatus getUserStatus() {
    return this.userStatus;
  }

}
