package com.myexam.controller.response;

import com.myexam.model.Recipe;
import java.util.List;
import lombok.Data;

@Data
public class RecipeGetResponse {

  String message;

  List<Recipe> recipe;

}
