package com.myexam.controller.request;

import lombok.Data;

@Data
public class RecipePatchRequest {

  String title;
  String making_time;
  String serves;
  String ingredients;
  long cost;

}
