package com.myexam.domain.service;

import com.myexam.domain.repositories.entity.RecipeEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {

  public void create();

  public void update();

  public void delete();

  List<RecipeEntity> list();

  public void getById();

}
