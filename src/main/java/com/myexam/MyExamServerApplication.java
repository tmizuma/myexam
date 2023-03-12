package com.myexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MyExamServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyExamServerApplication.class, args);
  }

}

