package com.myexam.domain.service;


import com.myexam.domain.repositories.RecipeRepository;
import com.myexam.domain.repositories.entity.RecipeEntity;
import com.myexam.model.Recipe;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("RecipeServiceImpl")
public class RecipeServiceImpl implements RecipeService {

  RecipeRepository repository;

  @Autowired
  public RecipeServiceImpl(RecipeRepository repository) {
    this.repository = repository;
  }

  @Override
  public void create() {

  }

  @Override
  public void update() {

  }

  @Override
  public void delete() {
  }

  @Override
  public List<Recipe> list() {
    var result = repository.findAll();
    List<Recipe> list = new ArrayList();
    result.forEach(v -> {
      var recipe = new Recipe();
      recipe.setId(v.getId());
      recipe.setTitle(v.getTitle());
      recipe.setServes(v.getServes());
      recipe.setMaking_time(v.getMaking_time());
      recipe.setCost(v.getCost());
      list.add(recipe);
    });

    return list;
  }


  @Override
  public List<Recipe> getById(Long id) {
    var result = repository.getOne(id);
    var recipe = new Recipe();
    recipe.setId(result.getId());
    recipe.setTitle(result.getTitle());
    recipe.setServes(result.getServes());
    recipe.setMaking_time(result.getMaking_time());
    recipe.setCost(result.getCost());
    List list = new ArrayList<RecipeEntity>();
    list.add(recipe);
    return list;
  }

}
