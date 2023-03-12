package com.myexam.domain.service.impl.account.domain.object;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class HashedPassword {

  public static HashedPassword of(String hashedPassword) {
    return new HashedPassword(hashedPassword);
  }

  public static HashedPassword fromRawPassword(String rawPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return new HashedPassword(passwordEncoder.encode(rawPassword));
  }

  private String hashedPassword;

  private HashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

}
