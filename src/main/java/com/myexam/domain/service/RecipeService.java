package com.myexam.domain.service;

import com.myexam.controller.request.RecipePatchRequest;
import com.myexam.controller.request.RecipePostRequest;
import com.myexam.model.Recipe;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {

  List<Recipe> create(RecipePostRequest recipe);

  List<Recipe> update(long id, RecipePatchRequest recipe);

  void delete(long id);

  List<Recipe> list();

  List<Recipe> getById(long id);
}
