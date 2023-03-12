package com.myexam.domain.repositories.email;

import com.myexam.infrastructure.repositoryImpl.email.EmailVerificationEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepository {

  Optional<EmailVerificationEntity> findById(String email);

  void save(EmailVerificationEntity user);

  void delete(String email);

}
