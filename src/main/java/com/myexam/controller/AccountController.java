package com.myexam.controller;

import com.myexam.controller.request.account.ActivateRequest;
import com.myexam.controller.request.account.SignUpRequest;
import com.myexam.controller.response.ExceptionResponse;
import com.myexam.controller.response.SignUpResponse;
import com.myexam.domain.exception.account.AlreadyUserExistsException;
import com.myexam.domain.exception.account.InvalidEmailVerificationCodeException;
import com.myexam.domain.exception.account.UserNotFoundException;
import com.myexam.domain.service.contract.account.AccountService;
import jakarta.validation.Valid;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/account")
public class AccountController {

  private AccountService accountService;

  private MessageSource messageSource;

  @Autowired
  public AccountController(
          @Qualifier("AccountServiceImpl") AccountService accountService,
          MessageSource messageSource) {
    this.accountService = accountService;
    this.messageSource = messageSource;
  }

  @PostMapping("/signup")
  public ResponseEntity<SignUpResponse> signup(
          @Valid @RequestBody SignUpRequest req
  ) {
    var response = accountService.signUp(req);
    return new ResponseEntity(
            response,
            HttpStatus.OK
    );
  }

  @PostMapping("/activate")
  public ResponseEntity activate(
          @Valid @RequestBody ActivateRequest req
  ) {
    if (accountService.activate(req)) {
      return new ResponseEntity(
              HttpStatus.OK
      );
    }
    return new ResponseEntity(
            HttpStatus.UNAUTHORIZED
    );
  }


  @ExceptionHandler(AlreadyUserExistsException.class)
  public ResponseEntity<ExceptionResponse> handleAlreadyUserExistsException(
          AlreadyUserExistsException exception) {
    return new ResponseEntity(
            new ExceptionResponse(exception.getMessage()),
            HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(InvalidEmailVerificationCodeException.class)
  public ResponseEntity<ExceptionResponse> handleInvalidEmailVerificationCodeException(
          InvalidEmailVerificationCodeException exception) {
    String message = messageSource.getMessage(InvalidEmailVerificationCodeException.messageKey,
            new String[]{exception.getMessage()},
            Locale.getDefault());
    return new ResponseEntity(
            new ExceptionResponse(message),
            HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleUserNotFoundException(
          UserNotFoundException exception) {
    String message = messageSource.getMessage(UserNotFoundException.messageKey,
            new String[]{exception.getMessage()},
            Locale.getDefault());
    return new ResponseEntity(
            new ExceptionResponse(message),
            HttpStatus.BAD_REQUEST
    );
  }
}
