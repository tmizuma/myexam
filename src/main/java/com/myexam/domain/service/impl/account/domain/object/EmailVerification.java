package com.myexam.domain.service.impl.account.domain.object;

import com.myexam.infrastructure.repositoryImpl.email.EmailVerificationEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

@Data
public class EmailVerification {

  String email;

  VerificationNonce nonce;

  VerificationCode verificationCode;

  public static long EXPIRE_SECONDS = 3600;

  public static EmailVerification of(@Valid @Email String email) {
    String nonce = generateMailVerificationNonceByEmail(email);
    String verificationCode = generateVerificationCode();
    return new EmailVerification(email, nonce, verificationCode);
  }

  public static EmailVerification fromDatasource(String email, VerificationNonce nonce,
          VerificationCode verificationCode) {
    return new EmailVerification(email, nonce.getValue(), verificationCode.getValue());
  }

  private EmailVerification(String email, String nonce, String verificationCode) {
    this.email = email;
    this.nonce = VerificationNonce.of(nonce);
    this.verificationCode = VerificationCode.of(verificationCode);
  }

  public EmailVerificationEntity convertToEntity() {
    EmailVerificationEntity entity = new EmailVerificationEntity();
    entity.setEmail(email);
    entity.setNonce(nonce.getValue());
    entity.setVerificationCode(verificationCode.getValue());
    entity.setTtl(generateTTL());
    return entity;
  }

  public boolean verify(String targetEmail, String targetEmailVerificationCode,
          String targetNonce) {
    if (verificationCode == null || nonce == null) {
      return false;
    }
    return email.equals(targetEmail)
            && verificationCode.getValue().equals(targetEmailVerificationCode)
            && nonce.getValue().equals(targetNonce);
  }

  private long generateTTL() {
    return current() + EXPIRE_SECONDS;
  }

  private long current() {
    return (System.currentTimeMillis() / 1000);
  }

  private boolean isExpired(long ttl) {
    // DynamoDBのTTL機能は必ずしもレコードがexpireのタイミングで削除されるとは限らない
    // そのため、アプリケーション側でのTTLの確認を行う必要がある
    // also see: https://docs.aws.amazon.com/ja_jp/amazondynamodb/latest/developerguide/howitworks-ttl.html
    return current() >= ttl;
  }

  private static String generateMailVerificationNonceByEmail(String email) {
    ByteBuffer buf = ByteBuffer.allocate(48);
    byte[] randomBytes = new byte[16];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(randomBytes);
    buf.put(randomBytes);

    byte[] emailHashBytes = DigestUtils.sha256(
            email + Hex.encodeHexString(randomBytes));
    buf.put(emailHashBytes);

    return Base64.encodeBase64URLSafeString(buf.array());
  }

  private static String generateVerificationCode() {
    Random random = new Random();
    String value = "0".repeat(VerificationCode.VERIFICATION_CODE_LENGTH) + random.nextInt(
            Integer.parseInt("9".repeat(VerificationCode.VERIFICATION_CODE_LENGTH)));
    return value.substring(value.length() - VerificationCode.VERIFICATION_CODE_LENGTH);
  }

}
