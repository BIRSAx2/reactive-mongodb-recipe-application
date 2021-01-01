package dev.mouhieddine.recipeapplication.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Getter
@Setter
@Document
public class Category {
  @Id
  private String id;
  private String description;

  @DBRef
  private Set<Recipe> recipes;
}
