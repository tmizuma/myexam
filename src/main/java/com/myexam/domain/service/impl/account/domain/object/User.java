package com.myexam.domain.service.impl.account.domain.object;

import com.myexam.domain.service.impl.account.UserStatus;
import com.myexam.infrastructure.repositoryImpl.user.UserEntity;
import lombok.Data;

@Data
public class User {

  /**
   * 新たにUserインスタンスを生成する
   *
   * @param id
   * @param hashedPassword
   * @return
   */
  public static User of(String id, HashedPassword hashedPassword) {
    return new User(id, hashedPassword, AuthenticationNextChallenge.NONE,
            UserStatus.NEED_TO_VERIFY_EMAIL);
  }

  /**
   * データベースなどのデータソースからUserインスタンスを生成する
   *
   * @param id
   * @param hashedPassword
   * @param challenge
   * @param userStatus
   * @return
   */
  public static User fromDatasource(String id,
          AuthenticationNextChallenge challenge, UserStatus userStatus) {
    return new User(id, null, challenge, userStatus);
  }

  public UserEntity convertToEntity() {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(this.id);
    userEntity.setChallenge(this.challenge);
    userEntity.setHashedPassword(this.hashedPassword.getHashedPassword());
    userEntity.setUserStatus(this.userStatus);
    return userEntity;
  }

  private User(String id, HashedPassword hashedPassword, AuthenticationNextChallenge challenge,
          UserStatus userStatus) {
    this.id = id;
    this.hashedPassword = hashedPassword;
    this.challenge = challenge;
    this.userStatus = userStatus;
  }

  private String id;
  private HashedPassword hashedPassword;
  private AuthenticationNextChallenge challenge;
  private UserStatus userStatus;

}
