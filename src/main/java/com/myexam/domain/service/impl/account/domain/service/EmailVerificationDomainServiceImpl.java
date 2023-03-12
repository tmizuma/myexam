package com.myexam.domain.service.impl.account.domain.service;

import com.myexam.domain.repositories.email.EmailVerificationRepository;
import com.myexam.domain.service.impl.account.domain.object.EmailVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UserDomainServiceImpl")
public class EmailVerificationDomainServiceImpl implements EmailVerificationDomainService {

  private EmailVerificationRepository emailVerificationRepository;

  @Autowired
  public EmailVerificationDomainServiceImpl(
          EmailVerificationRepository emailVerificationRepository) {
    this.emailVerificationRepository = emailVerificationRepository;
  }

  public void upsert(EmailVerification emailVerification) {
    var entity = emailVerification.convertToEntity();
    var result = emailVerificationRepository.findById(emailVerification.getEmail());
    if (result.isPresent()) {
      emailVerificationRepository.delete(emailVerification.getEmail());
    }
    emailVerificationRepository.save(entity);
  }
}
