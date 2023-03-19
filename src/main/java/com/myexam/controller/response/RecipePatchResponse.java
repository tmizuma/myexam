package com.myexam.controller.response;

import com.myexam.domain.repositories.entity.RecipeEntity;
import com.myexam.model.Recipe;
import java.util.List;
import lombok.Data;

@Data
public class RecipePatchResponse {

  String message;

  List<Recipe> recipe;

}
