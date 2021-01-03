package dev.mouhieddine.recipeapplication.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Getter
@Setter
public class Ingredient {

  private String id = UUID.randomUUID().toString();
  private String description;
  private BigDecimal amount;
  private UnitOfMeasure uom;

  public Ingredient() {

  }

  public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
    this.description = description;
    this.amount = amount;
    this.uom = uom;
  }

}