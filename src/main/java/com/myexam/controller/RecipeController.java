package com.myexam.controller;

import com.myexam.controller.request.RecipePostRequest;
import com.myexam.controller.response.RecipeDeleteResponse;
import com.myexam.controller.response.RecipeGetResponse;
import com.myexam.controller.response.RecipeListResponse;
import com.myexam.controller.response.RecipePostResponse;
import com.myexam.domain.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    response.setRecipes(result);

    // データベース全てのレシピを返す
    return new ResponseEntity(
            response,
            HttpStatus.OK
    );
  }

  @GetMapping("recipes/{id}")
  public ResponseEntity<RecipeGetResponse> getById(@PathVariable String id) {

    var result = service.getById(Long.parseLong(id));
    var response = new RecipeGetResponse();

    response.setMessage("Recipe details by id");
    response.setRecipe(result);

    return new ResponseEntity(
            response,
            HttpStatus.OK
    );
  }

  @DeleteMapping("recipes/{id}")
  public ResponseEntity<RecipeDeleteResponse> deleteById(@PathVariable String id) {

    try {
      service.delete(Long.parseLong(id));
      var response = new RecipeDeleteResponse();
      response.setMessage("Recipe successfully removed!");
      // データベース全てのレシピを返す
      return new ResponseEntity(
              response,
              HttpStatus.OK
      );
    } catch (RuntimeException e) {
      // Todo Error logging
    }
    var response = new RecipeDeleteResponse();
    response.setMessage("No Recipe found");
    return new ResponseEntity(
            response,
            HttpStatus.BAD_REQUEST
    );
  }

  @PostMapping("recipes")
  public ResponseEntity post(@Valid @RequestBody RecipePostRequest req) {

    var result = service.create(req);
    var response = new RecipePostResponse();
    response.setMessage("Recipe successfully created!");
    response.setRecipe(result);
    // データベース全てのレシピを返す
    return new ResponseEntity(
            response,
            HttpStatus.OK
    );
  }

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