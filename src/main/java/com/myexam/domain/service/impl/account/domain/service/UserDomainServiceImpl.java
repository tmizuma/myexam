package com.myexam.domain.service.impl.account.domain.service;

import com.myexam.domain.repositories.user.UserRepository;
import com.myexam.domain.service.impl.account.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UserDomainServiceImpl")
public class UserDomainServiceImpl implements UserDomainService {

  private UserRepository userRepository;

  @Autowired
  public UserDomainServiceImpl(@Qualifier("UserDynamoDBRepository") UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean checkIfBeforeSignUpCompletionUserById(String id) {
    var result = userRepository.findById(id);
    if (!result.isPresent()) {
      return true;
    }
    var user = result.get();
    return user.getUserStatus().equals(UserStatus.NEED_TO_VERIFY_EMAIL);
  }

  @Override
  public boolean checkIfActiveUserById(String id) {
    var result = userRepository.findById(id);
    if (!result.isPresent()) {
      return false;
    }
    var user = result.get();
    return user.getUserStatus().equals(UserStatus.ACTIVE);
  }
}
