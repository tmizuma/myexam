package com.myexam.domain.service.user;

import com.myexam.controller.request.user.SignUpRequest;
import com.myexam.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  User signup(SignUpRequest req);

}
