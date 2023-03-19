package com.myexam.controller;

import com.myexam.controller.response.ExceptionResponse;
import com.myexam.exception.RecipeDuplicatedException;
import com.myexam.exception.RecipeNotFoundException;
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
   * 対象の要素が見つからなかった場合のハンドリング
   * @param exception
   * @return
   */
  @ExceptionHandler(RecipeNotFoundException.class)
  protected ResponseEntity handleRecipeNotFoundException(RecipeNotFoundException exception) {
    return new ResponseEntity(
            new ExceptionResponse(exception.getMessage()),
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
