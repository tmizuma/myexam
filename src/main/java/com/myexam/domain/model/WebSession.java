package com.myexam.domain.model;

import com.myexam.domain.service.impl.account.domain.object.AuthenticationNextChallenge;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * IDEA の Webセッション保持用のオブジェクト
 */
@Data
public class WebSession implements UserDetails {

  private WebSession(String sub, String hashedPass, AuthenticationNextChallenge challenge) {
    this.sub = sub;
    this.hashedPass = hashedPass;
    this.challenge = challenge;
  }

  public static WebSession of(String sub, String hashedPass,
          AuthenticationNextChallenge challenge) {
    return new WebSession(sub, hashedPass, challenge);
  }

  private String sub;
  private String hashedPass;
  private AuthenticationNextChallenge challenge;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPassword() {
    return this.hashedPass;
  }

  @Override
  public String getUsername() {
    return this.sub;
  }

  @Override
  public boolean isAccountNonExpired() {
    // ToDo Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // ToDo Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // ToDo Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // ToDo Auto-generated method stub
    return true;
  }
}
