package com.myexam.controller;

import com.myexam.controller.response.ExceptionResponse;
import com.myexam.controller.response.RuntimeExceptionResponse;
import com.myexam.exception.UserDuplicatedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * 対象の要素が重複場合のハンドリング
   * @param exception
   * @return
   */
  @ExceptionHandler(UserDuplicatedException.class)
  protected ResponseEntity handleUserDuplicatedException(UserDuplicatedException exception) {
    String message = exception.getMessage().split("/")[0];
    String cause = exception.getMessage().split("/")[1];
    return new ResponseEntity(
            new RuntimeExceptionResponse(message, cause),
            HttpStatus.NOT_FOUND // ToDo: HTTP Status Codeをチェック
    );
  }

  /**
   * リクエストオブジェクトに不正な値が渡された場合のハンドリング
   *
   * @param ex the exception to handle
   * @param headers the headers to be written to the response
   * @param status the selected response status
   * @param request the current request
   * @return
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
    return new ResponseEntity(new ExceptionResponse(message), HttpStatus.BAD_REQUEST);
  }

  /**
   * その他RuntimeExceptionのハンドリング
   *
   * @param exception
   * @return
   */
  @ExceptionHandler(RuntimeException.class)
  protected ResponseEntity handleRuntimeException(RuntimeException exception) {
    return new ResponseEntity(
            new ExceptionResponse(exception.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  /**
   * その他Exceptionのハンドリング
   *
   * @param exception
   * @return
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity handleException(Exception exception) {
    return new ResponseEntity(
            new ExceptionResponse(exception.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

}
