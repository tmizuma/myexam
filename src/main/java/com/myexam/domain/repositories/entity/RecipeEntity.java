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
@Table(name = "recipes")
public class RecipeEntity {

  /**
   * ID
   */
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title")
  private String title;
  @Column(name = "making_time")
  private String making_time;

  @Column(name = "serves")
  private String serves;

  @Column(name = "ingredients")
  private String ingredients;

  @Column(name = "cost")
  private long cost;

  @Column(name = "updated_at")
  private Date updated_at;
  @Column(name = "created_at")
  private Date created_at;

}
