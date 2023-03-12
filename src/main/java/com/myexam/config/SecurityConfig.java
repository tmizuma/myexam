package com.myexam.config;

import com.myexam.config.application.JwtConfig;
import com.myexam.domain.service.contract.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  JwtConfig jwtConfig;

  MessageSource messageSource;

  AccountService accountService;

  @Autowired
  public SecurityConfig(JwtConfig jwtConfig, AccountService accountService,
          MessageSource messageSource) {
    this.jwtConfig = jwtConfig;
    this.messageSource = messageSource;
    this.accountService = accountService;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers("/api/authz/authorization/issue")
            .authenticated()
            .and()
            .addFilterBefore(new JwtTokenFilter(jwtConfig, accountService, messageSource),
                    UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

}