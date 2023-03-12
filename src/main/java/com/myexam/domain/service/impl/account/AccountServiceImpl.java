package com.myexam.domain.service.impl.account;

import com.myexam.controller.request.account.ActivateRequest;
import com.myexam.controller.request.account.SignUpRequest;
import com.myexam.controller.response.SignUpResponse;
import com.myexam.domain.exception.account.AlreadyUserExistsException;
import com.myexam.domain.exception.account.InvalidEmailVerificationCodeException;
import com.myexam.domain.repositories.email.EmailVerificationRepository;
import com.myexam.domain.repositories.user.UserRepository;
import com.myexam.domain.service.contract.account.AccountService;
import com.myexam.domain.service.impl.account.domain.object.EmailVerification;
import com.myexam.domain.service.impl.account.domain.object.HashedPassword;
import com.myexam.domain.service.impl.account.domain.object.User;
import com.myexam.domain.service.impl.account.domain.object.VerificationCode;
import com.myexam.domain.service.impl.account.domain.object.VerificationNonce;
import com.myexam.domain.service.impl.account.domain.service.EmailVerificationDomainService;
import com.myexam.domain.service.impl.account.domain.service.UserDomainService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

  private UserRepository userRepository;

  private EmailVerificationRepository emailVerificationRepository;

  private UserDomainService userDomainService;

  private EmailVerificationDomainService emailVerificationDomainService;

  @Autowired
  public AccountServiceImpl(
          UserRepository userRepository,
          EmailVerificationRepository emailVerificationRepository,
          UserDomainService userDomainService,
          EmailVerificationDomainService emailVerificationDomainService,
          MessageSource messageSource) {
    this.userRepository = userRepository;
    this.emailVerificationRepository = emailVerificationRepository;
    this.userDomainService = userDomainService;
    this.emailVerificationDomainService = emailVerificationDomainService;
  }

  @Override
  public SignUpResponse signUp(SignUpRequest req) throws AlreadyUserExistsException {

    HashedPassword hashedPassword = HashedPassword.fromRawPassword(req.getPassword());
    User user = User.of(req.getId(), hashedPassword);

    if (userDomainService.checkIfBeforeSignUpCompletionUserById(user.getId())) {
      throw new AlreadyUserExistsException(user.getId());
    }

    String email = req.getId(); // WNではemailが事実上のIDとなっている
    EmailVerification emailVerification = EmailVerification.of(email);

    // DynamoDBへの書き込み
    emailVerificationDomainService.upsert(emailVerification);
    userRepository.save(user.convertToEntity());

    // ToDo: send email

    return new SignUpResponse(emailVerification.getNonce().getValue());
  }

  @Override
  public boolean activate(ActivateRequest req) {
    String email = req.getId();
    var result = emailVerificationRepository.findById(email);

    if (!result.isPresent()) {
      throw new InvalidEmailVerificationCodeException(null);
    }

    VerificationNonce nonce = VerificationNonce.of(result.get().getNonce());
    VerificationCode code = VerificationCode.of(result.get().getVerificationCode());

    EmailVerification emailVerification =
            EmailVerification.fromDatasource(result.get().getEmail(), nonce, code);
    return emailVerification.verify(email, req.getVerificationCode(), req.getNonce());
  }

  @Override
  public boolean checkIfExistsById(String id) {
    return userDomainService.checkIfActiveUserById(id);
  }

  @Override
  public Optional<User> authenticate(String id, String password) {
    var result = userRepository.findById(id);
    if (!result.isPresent()) {
      return Optional.empty();
    }

    // ToDo: Challengeに合わせた認証ロジックの実装
    // memo: - Challenge は配列でもつ
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (passwordEncoder.matches(password, result.get().getHashedPassword())) {
      return Optional.of(User.fromDatasource(result.get().getId(), result.get().getNextChallenge(),
              result.get().getUserStatus()));
    }
    return null;
  }


}