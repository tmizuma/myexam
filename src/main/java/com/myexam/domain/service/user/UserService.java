package com.myexam.domain.service.user;

import com.myexam.controller.request.user.SignUpRequest;
import com.myexam.model.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  User signup(SignUpRequest req);

  User getById(String userId, String authorization);
}
