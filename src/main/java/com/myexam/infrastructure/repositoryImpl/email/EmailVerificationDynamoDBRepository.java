package com.myexam.infrastructure.repositoryImpl.email;

import com.myexam.domain.repositories.email.EmailVerificationRepository;
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
@Qualifier("EmailVerificationDynamoDBRepository")
public class EmailVerificationDynamoDBRepository implements EmailVerificationRepository {

  private final DynamoDbEnhancedClient client;

  private final DynamoDbTable<EmailVerificationEntity> table;

  private final String tableName;

  @Autowired
  public EmailVerificationDynamoDBRepository(DynamoDbEnhancedClient client) {
    this.client = client;
    this.tableName = "email-verification";
    this.table = client.table(this.tableName,
            TableSchema.fromBean(EmailVerificationEntity.class));
  }

  @Override
  public Optional<EmailVerificationEntity> findById(String id) {
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
  public void save(EmailVerificationEntity entity) {
    table.putItem(entity);
  }

  @Override
  public void delete(String email) {
    Key key = Key.builder()
            .partitionValue(email)
            .build();
    table.deleteItem(key);
  }
}
