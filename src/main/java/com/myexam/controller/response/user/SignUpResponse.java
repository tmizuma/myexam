package com.myexam.controller.response.user;

import com.myexam.model.User;
import lombok.Data;

@Data
public class SignUpResponse {

  String message;

  User user;

}
