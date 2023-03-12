package com.myexam.controller;

import com.myexam.controller.response.RecipeListResponse;
import com.myexam.domain.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RecipeController {

  RecipeService service;

  @Autowired
  public RecipeController(@Qualifier("RecipeServiceImpl") RecipeService service) {
    this.service = service;
  }

  @GetMapping("/recipes")
  public ResponseEntity<RecipeListResponse> list() {

    var result = service.list();
    var response = new RecipeListResponse();
    response.setRecipe(result);

    // データベース全てのレシピを返す
    return new ResponseEntity(
            response,
            HttpStatus.OK
    );
  }
//
//  @GetMapping("recipes/${id}")
//  public ResponseEntity getById() {
//
//    // データベース全てのレシピを返す
//    return new ResponseEntity(
//            "Health Check OK",
//            HttpStatus.OK
//    );
//  }
//
//  @DeleteMapping("recipes/${id}")
//  public ResponseEntity delete() {
//
//    // データベース全てのレシピを返す
//    return new ResponseEntity(
//            "Health Check OK",
//            HttpStatus.OK
//    );
//  }
//
//  @PostMapping("recipes")
//  public ResponseEntity post() {
//
//    // データベース全てのレシピを返す
//    return new ResponseEntity(
//            "Health Check OK",
//            HttpStatus.OK
//    );
//  }

//  @PatchMapping("recipes/${id}")
//  public ResponseEntity patch() {
//
//    // データベース全てのレシピを返す
//    return new ResponseEntity(
//            "Health Check OK",
//            HttpStatus.OK
//    );
//  }
}