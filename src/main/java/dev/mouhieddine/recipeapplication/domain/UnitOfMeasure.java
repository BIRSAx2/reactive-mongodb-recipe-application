package dev.mouhieddine.recipeapplication.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Getter
@Setter
@Document
public class UnitOfMeasure {

  @Id
  private String id;
  private String description;
}