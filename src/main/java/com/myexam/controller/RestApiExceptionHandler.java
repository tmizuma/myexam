package com.myexam.controller;

import com.myexam.controller.response.ExceptionResponse;
import com.myexam.exception.RecipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

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

  @ExceptionHandler(RecipeNotFoundException.class)
  protected ResponseEntity handleRecipeNotFoundException(RecipeNotFoundException exception) {
    return new ResponseEntity(
            new ExceptionResponse(exception.getMessage()),
            HttpStatus.CONFLICT // ToDo: HTTP Status Codeをチェック
    );
  }

}
