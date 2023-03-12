package com.myexam.model;

import lombok.Data;

@Data
public class Recipe {

  private Long id;
  private String title;
  private String making_time;
  private String serves;
  private String ingredients;
  private long cost;
  
}
