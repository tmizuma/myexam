package com.myexam.domain.service.impl.account.domain.service;

public interface UserDomainService {

  boolean checkIfBeforeSignUpCompletionUserById(String id);

  boolean checkIfActiveUserById(String id);

}
