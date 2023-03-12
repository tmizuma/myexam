package com.myexam.config;

import com.myexam.domain.model.WebSession;
import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    String id = auth.getName();
    String hashedPass = auth.getCredentials().toString();

    if ("".equals(id) || "".equals(hashedPass)) {
      throw new AuthenticationCredentialsNotFoundException("Invalid ID or Password");
    }

    // ToDo: Implement the authentication process
    WebSession user = WebSession.of(id, hashedPass, AuthenticationNextChallenge.NONE);

    if (user == null) {
      throw new AuthenticationCredentialsNotFoundException("ログイン情報が存在しません。");
    }
    // トークンを返却
    return new UsernamePasswordAuthenticationToken(user, hashedPass, auth.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return false;
  }

}
