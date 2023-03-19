package com.myexam.controller;

import com.myexam.controller.request.user.SignUpRequest;
import com.myexam.controller.response.user.SignUpResponse;
import com.myexam.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    var result = service.signup(req);
    var response = new SignUpResponse();
    response.setMessage("Account successfully created");
    response.setUser(result);
    return new ResponseEntity(
            response,
            HttpStatus.OK
    );
  }
}