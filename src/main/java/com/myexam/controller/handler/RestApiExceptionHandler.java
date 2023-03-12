package com.myexam.controller.handler;

import com.myexam.controller.response.ExceptionResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  public RestApiExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity handleException(Exception exception) {
    return new ResponseEntity(
            new ExceptionResponse(exception.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity handleRuntimeException(RuntimeException exception) {
    return new ResponseEntity(
            new ExceptionResponse(exception.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

}
