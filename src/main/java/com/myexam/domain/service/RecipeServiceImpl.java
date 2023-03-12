package com.myexam.domain.service;


import com.myexam.domain.repositories.RecipeRepository;
import com.myexam.domain.repositories.entity.RecipeEntity;
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

  public void create() {

  }

  public void update() {

  }

  public void delete() {
  }


  public List<RecipeEntity> list() {
    return repository.findAll();
  }


  public void getById() {

  }

}
