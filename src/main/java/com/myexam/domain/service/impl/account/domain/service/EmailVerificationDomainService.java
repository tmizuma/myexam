package com.myexam.domain.service.impl.account.domain.service;

import com.myexam.domain.service.impl.account.domain.object.EmailVerification;

public interface EmailVerificationDomainService {

  public void upsert(EmailVerification emailVerification);

}
