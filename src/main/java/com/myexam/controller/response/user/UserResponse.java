package com.myexam.controller.response.user;

import com.myexam.model.User;
import lombok.Data;

@Data
public class UserResponse {

  String message;

  User user;

}
