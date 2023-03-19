package com.myexam.model;

import com.myexam.domain.repositories.entity.RecipeEntity;
import lombok.Data;

@Data
public class Recipe {

  private Recipe(long id, String title, String making_time, String serves, String ingredients, long cost) {
    this.id = id;
    this.title = title;
    this.making_time = making_time;
    this.serves = serves;
    this.ingredients = ingredients;
    this.cost = cost;

  }

  private Recipe(RecipeEntity entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.making_time = entity.getMaking_time();
    this.serves = entity.getServes();
    this.ingredients = entity.getIngredients();
    this.cost = entity.getCost();
  }

  public static Recipe of(long id, String title, String making_time, String serves, String ingredients, long cost) {
    return new Recipe(id, title, making_time, serves, ingredients, cost);
  }


  public static Recipe fromDatasource(RecipeEntity entity) {
    return new Recipe(entity);
  }

  private Long id;
  private String title;
  private String making_time;
  private String serves;
  private String ingredients;
  private long cost;

}
