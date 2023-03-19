package com.myexam.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecipePatchRequest {

  @NotNull(message = "The field 'title' cannot be empty.")
  @NotBlank(message = "The field 'title' is required and cannot be left blank.")
  String title;
  @NotNull(message = "The field 'making_time' cannot be empty.")
  @NotBlank(message = "The field 'making_time' is required and cannot be left blank.")
  String making_time;
  @NotNull(message = "The field 'serves' cannot be empty.")
  @NotBlank(message = "The field 'serves' is required and cannot be left blank.")
  String serves;
  @NotNull(message = "The field 'ingredients' cannot be empty.")
  @NotBlank(message = "The field 'ingredients' is required and cannot be left blank.")
  String ingredients;

  @Min(value = 0, message = "The field 'cost' must have a value greater than or equal to 0.")
  @NotNull(message = "The field 'cost' cannot be empty.")
  long cost;

}
