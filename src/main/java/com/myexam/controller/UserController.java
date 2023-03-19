package com.myexam.controller;

import com.myexam.controller.request.user.SignUpRequest;
import com.myexam.controller.response.RuntimeExceptionResponse;
import com.myexam.controller.response.RuntimeExceptionWithoutCauseResponse;
import com.myexam.controller.response.user.SignUpResponse;
import com.myexam.controller.response.user.UserResponse;
import com.myexam.domain.service.user.UserService;
import com.myexam.exception.AuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

  UserService service;

  @Autowired
  public UserController(@Qualifier("UserServiceImpl") UserService service) {
    this.service = service;
  }


  @PostMapping("signup")
  public ResponseEntity post(@RequestBody @Validated SignUpRequest req) {
    // ASCII/空白文字のバリデーション
    var result = service.signup(req);
    var response = new SignUpResponse();
    response.setMessage("Account successfully created");
    response.setUser(result);
    return new ResponseEntity(
            response,
            HttpStatus.OK
    );
  }

  @GetMapping("users/{user_id}")
  public ResponseEntity<UserResponse> getById(HttpServletRequest httpRequest,@PathVariable String user_id) {
    String authorization = httpRequest.getHeader("Authorization");
    if (authorization == null) {
      throw new AuthenticationException("Authentication Failed");
    }
    var result = service.getById(user_id, authorization);
    var response = new UserResponse();
    response.setMessage("User details by user_id");
    response.setUser(result);
    return new ResponseEntity(
            response,
            HttpStatus.OK
    );

  }

}