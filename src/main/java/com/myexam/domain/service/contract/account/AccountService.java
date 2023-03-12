package com.myexam.domain.service.contract.account;

import com.myexam.controller.request.account.ActivateRequest;
import com.myexam.controller.request.account.SignUpRequest;
import com.myexam.controller.response.SignUpResponse;
import com.myexam.domain.exception.account.AlreadyUserExistsException;
import com.myexam.domain.service.impl.account.domain.object.User;
import java.util.Optional;

public interface AccountService {

  SignUpResponse signUp(SignUpRequest req) throws AlreadyUserExistsException;

  boolean activate(ActivateRequest req);

  boolean checkIfExistsById(String id);

  Optional<User> authenticate(String id, String password);
}
