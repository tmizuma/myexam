package com.myexam.module;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.myexam.config.application.JwtConfig;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenModule {

  JwtConfig jwtConfig;

  @Autowired
  public JwtTokenModule(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  private static final Long EXPIRATION_TIME = 1000L * 60L * 10L;

  public String create(String sub) {
    String secretKey = jwtConfig.getSecret();
    Date issuedAt = new Date();
    Date notBefore = new Date(issuedAt.getTime());
    Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);

    String token = "";
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      token = JWT.create()
              .withSubject(sub)
              .withIssuedAt(issuedAt)
              .withNotBefore(notBefore)
              .withExpiresAt(expiresAt)
              .sign(algorithm);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return token;
  }
}
