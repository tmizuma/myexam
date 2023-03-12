package com.myexam.controller.response;

import com.myexam.domain.repositories.entity.RecipeEntity;
import lombok.Data;

@Data
public class RecipePostResponse {

  String message;

  RecipeEntity[] recipe;

}
