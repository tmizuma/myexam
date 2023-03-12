package com.myexam.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

  private String dynamoDbEndPointUrl;

  private Region region;

  public DynamoDBConfig(@Value("${aws.dynamodb.endpoint}") String dynamoDbEndPointUrl,
          @Value("${aws.dynamodb.region}") String region) {
    this.dynamoDbEndPointUrl = dynamoDbEndPointUrl;
    switch (region) {
      case "ap-northeast-1":
        this.region = Region.AP_NORTHEAST_1;
        break;
      case "us-east-1":
        this.region = Region.US_EAST_1;
        break;
      default:
        this.region = null;
    }
  }

  @Bean
  public DynamoDbClient getDynamoDbClient() {
    return DynamoDbClient.builder()
            .region(Region.AP_NORTHEAST_1)
            .endpointOverride(URI.create(dynamoDbEndPointUrl))
            .build();
  }

  @Bean
  public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
    return DynamoDbEnhancedClient.builder()
            .dynamoDbClient(getDynamoDbClient())
            .build();
  }

}