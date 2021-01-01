package dev.mouhieddine.recipeapplication.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Getter
@Setter
public class Notes {

  @Id
  private String id;
  private String recipeNotes;
}
