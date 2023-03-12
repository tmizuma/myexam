package com.myexam.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class DispatcherServletCustomConfiguration {

  @Bean
  public DispatcherServlet dispatcherServlet() {
    return new DispatcherServlet();
  }

  @SuppressWarnings("rawtypes")
  @Bean
  public ServletRegistrationBean axisServletRegistrationBean() {
    @SuppressWarnings("unchecked")
    ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/*");
    registration.addUrlMappings("/api/*");
    return registration;
  }
}