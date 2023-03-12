package com.myexam.controller.response;

import com.myexam.domain.repositories.entity.RecipeEntity;
import java.util.List;
import lombok.Data;

@Data
public class RecipeListResponse {

  List<RecipeEntity> recipe;

}
