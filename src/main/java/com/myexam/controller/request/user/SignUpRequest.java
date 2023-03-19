package com.myexam.controller.request.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignUpRequest {

  @NotNull(message = "Account creation failed")
  @NotBlank(message = "required user_id and password")
  @Length(min=6, max=20, message = "The user_id should have a length ranging from 6 to 20 characters.")
  String user_id;
  @NotNull(message = "Account creation failed")
  @NotBlank(message = "required user_id and password")
  @Length(min=8, max=20, message = "The password should have a length ranging from 6 to 20 characters.")
  String password;

  String nickname;

  String comment;

}
