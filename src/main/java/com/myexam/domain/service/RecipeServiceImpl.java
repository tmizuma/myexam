package com.myexam.domain.service;


import com.myexam.controller.request.RecipePatchRequest;
import com.myexam.controller.request.RecipePostRequest;
import com.myexam.domain.repositories.RecipeRepository;
import com.myexam.domain.repositories.entity.RecipeEntity;
import com.myexam.exception.RecipeNotFoundException;
import com.myexam.model.Recipe;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
  public List<Recipe> create(RecipePostRequest recipe) {
    RecipeEntity entity = new RecipeEntity();
    entity.setTitle(recipe.getTitle());
    entity.setMaking_time(recipe.getMaking_time());
    entity.setServes(recipe.getServes());
    entity.setIngredients(recipe.getIngredients());
    entity.setCost(recipe.getCost());
    entity.setCreated_at(new Date());
    entity.setUpdated_at(new Date());
    var result = repository.saveAndFlush(entity);

    var newRecipe = new Recipe();
    newRecipe.setId(result.getId());
    newRecipe.setTitle(result.getTitle());
    newRecipe.setServes(result.getServes());
    newRecipe.setMaking_time(result.getMaking_time());
    newRecipe.setCost(result.getCost());
    List list = new ArrayList<RecipeEntity>();
    list.add(newRecipe);
    return list;
  }

  @Override
  public List<Recipe> update(long id, RecipePatchRequest recipe) {

    Optional<RecipeEntity> existingRecipe = repository.findById(id);

    if (existingRecipe.isEmpty()) {
      throw new RecipeNotFoundException("Recipe id is not found");
    }
    var entity = existingRecipe.get();
    entity.setTitle(recipe.getTitle());
    entity.setMaking_time(recipe.getMaking_time());
    entity.setServes(recipe.getServes());
    entity.setIngredients(recipe.getIngredients());
    entity.setCost(recipe.getCost());
    entity.setUpdated_at(new Date());
    //    entity.setCreated_at(new Date()); // 作成日付は更新しない
    var result = repository.saveAndFlush(entity);

    var newRecipe = new Recipe();
    newRecipe.setId(result.getId());
    newRecipe.setTitle(result.getTitle());
    newRecipe.setServes(result.getServes());
    newRecipe.setMaking_time(result.getMaking_time());
    newRecipe.setCost(result.getCost());
    List list = new ArrayList<RecipeEntity>();
    list.add(newRecipe);
    return list;
  }

  @Override
  public void delete(long id) {
    repository.deleteById(id);
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
  public List<Recipe> getById(long id) {
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
