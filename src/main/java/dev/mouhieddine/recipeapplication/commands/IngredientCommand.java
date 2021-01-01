package dev.mouhieddine.recipeapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
  private String id;
  private String recipeId;
  private String description;
  private BigDecimal amount;
  private UnitOfMeasureCommand uom;
}