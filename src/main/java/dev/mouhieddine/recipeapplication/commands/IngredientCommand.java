package dev.mouhieddine.recipeapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
  @NotBlank
  private String description;
  @NotNull
  @Min(1)
  private BigDecimal amount;
  @NotNull
  private UnitOfMeasureCommand uom;
}