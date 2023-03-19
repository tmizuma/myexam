package com.myexam.domain.repositories.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

  @Id
  @Column(name = "user_id")
  private String user_id;
  @Column(name = "password")
  private String password;
  @Column(name = "nickname")
  private String nickname;
  @Column(name = "comment")
  private String comment;
  @Column(name = "updated_at")
  private Date updated_at;
  @Column(name = "created_at")
  private Date created_at;

}
