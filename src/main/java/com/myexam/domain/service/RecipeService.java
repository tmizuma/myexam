package com.myexam.domain.service;

import com.myexam.model.Recipe;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {

  public void create();

  public void update();

  public void delete();

  List<Recipe> list();

  List<Recipe> getById(Long id);
}
