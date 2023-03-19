package com.myexam.domain.service.user;

import com.myexam.controller.request.user.SignUpRequest;
import com.myexam.domain.repositories.UserRepository;
import com.myexam.domain.repositories.entity.UserEntity;
import com.myexam.exception.AuthenticationException;
import com.myexam.exception.UserDuplicatedException;
import com.myexam.exception.UserNotFoundException;
import com.myexam.model.User;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Qualifier("UserServiceImpl")
public class UserServiceImpl implements UserService {

  UserRepository repository;

  @Autowired
  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public User signup(SignUpRequest req) {
    Optional<UserEntity> existingUser = repository.findById(req.getUser_id());

    if (existingUser.isPresent()) {
      throw new UserDuplicatedException("Account creation failed", "already same user_id is used");
    }

    UserEntity userEntity = new UserEntity();
    userEntity.setUser_id(req.getUser_id());
    userEntity.setNickname(req.getNickname());
    userEntity.setComment(req.getComment());
    userEntity.setPassword(encode(req.getPassword()));
    userEntity.setUpdated_at(new Date());
    userEntity.setCreated_at(new Date());
    repository.save(userEntity);

    return new User(req.getUser_id(), req.getComment());
  }

  @Override
  public User getById(String userId, String authorization) {
    if (authorization.split(" ").length != 2) {
      throw new AuthenticationException("Authorization Failed");
    }
    String rawAuthorizationData = decode(authorization.split(" ")[1]);
    String decodedUserId = "";
    String decodedPassword = "";

    try {
       decodedUserId = rawAuthorizationData.split(":")[0];
       decodedPassword = rawAuthorizationData.split(":")[1];
    } catch (RuntimeException e) {
      // 不正なヘッダーフォーマットのため
      throw new AuthenticationException("Authorization Failed");
    }
    if (!userId.equals(decodedUserId)) {
      new AuthenticationException("Authorization Failed");
    }

    Optional<UserEntity> user = repository.findById(userId);
    if (user.isEmpty()) {
      throw new UserNotFoundException("No User found");
    }

    if (!decode(user.get().getPassword()).equals(decodedPassword)) {
      // パスワードが一致しないため
      throw new AuthenticationException("Authorization Failed");
    }
    return new User(user.get().getUser_id(), user.get().getComment());
  }


  private String encode(String rawData) {
    return Base64.getEncoder().encodeToString(rawData.getBytes());
  }

  private String decode(String encodedData) {
    return new String(Base64.getDecoder().decode(encodedData));
  }
}
