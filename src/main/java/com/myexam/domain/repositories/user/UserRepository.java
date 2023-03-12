package com.myexam.domain.repositories.user;

import com.myexam.infrastructure.repositoryImpl.user.UserEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

  Optional<UserEntity> findById(String sub);

  void save(UserEntity user);

}
