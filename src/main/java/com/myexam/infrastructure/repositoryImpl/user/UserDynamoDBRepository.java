package com.myexam.infrastructure.repositoryImpl.user;

import com.myexam.domain.repositories.user.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Repository
@Qualifier("UserDynamoDBRepository")
public class UserDynamoDBRepository implements UserRepository {

  private final DynamoDbEnhancedClient client;

  private final DynamoDbTable<UserEntity> table;

  private final String tableName;

  @Autowired
  public UserDynamoDBRepository(DynamoDbEnhancedClient client) {
    this.client = client;
    this.tableName = "user";
    this.table = client.table(this.tableName,
            TableSchema.fromBean(UserEntity.class));
  }

  @Override
  public Optional<UserEntity> findById(String id) {
    try {
      // Get the item by using the key.
      Key key = Key.builder()
              .partitionValue(id)
              .build();
      var user = table.getItem(
              (GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
      return Optional.of(user);
    } catch (DynamoDbException e) {
      // ToDo: error handling
    }
    return Optional.empty();
  }

  @Override
  public void save(UserEntity user) {
    table.putItem(user);
  }
}
