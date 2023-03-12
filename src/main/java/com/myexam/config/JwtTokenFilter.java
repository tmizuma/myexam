package com.myexam.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.myexam.config.application.JwtConfig;
import com.myexam.domain.exception.account.UserNotFoundException;
import com.myexam.domain.model.WebSession;
import com.myexam.domain.service.contract.account.AccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

  JwtConfig jwtConfig;

  AccountService accountService;

  MessageSource messageSource;

  public JwtTokenFilter(JwtConfig jwtConfig, AccountService accountService,
          MessageSource messageSource) {
    this.jwtConfig = jwtConfig;
    this.accountService = accountService;
    this.messageSource = messageSource;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    String token = resolveToken(request);
    if (token == null) {
      chain.doFilter(request, response);
      return;
    }

    String secretKey = jwtConfig.getSecret();
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      var user = instantiateUserByJwt(verifyToken(token, algorithm));
      SecurityContextHolder.getContext()
              .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, null));

    } catch (JWTVerificationException | UserNotFoundException e) {
      SecurityContextHolder.clearContext();
    }
    chain.doFilter(request, response);
  }

  private String resolveToken(ServletRequest request) {
    String token = ((HttpServletRequest) request).getHeader("Authorization");
    if (token == null || !token.startsWith("Bearer ")) {
      return null;
    }
    return token.substring(7);
  }

  private DecodedJWT verifyToken(String token, Algorithm algorithm) {
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

  private WebSession instantiateUserByJwt(DecodedJWT jwt) throws UserNotFoundException {
    String id = jwt.getSubject();
    if (accountService.checkIfExistsById(id)) {
      throw new UserNotFoundException(id);
    }
    return WebSession.of(id, "", null);
  }

}
