package dev.mouhieddine.recipeapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
  private String id;
  private String description;
}
