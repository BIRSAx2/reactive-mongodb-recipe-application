package dev.mouhieddine.recipeapplication.converters;


import dev.mouhieddine.recipeapplication.commands.IngredientCommand;
import dev.mouhieddine.recipeapplication.domain.Ingredient;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class IngredientToIngredientCommandTest {

  public static final Recipe RECIPE = new Recipe();
  public static final BigDecimal AMOUNT = new BigDecimal("1");
  public static final String DESCRIPTION = "Cheeseburger";
  public static final String UOM_ID = "2";
  public static final String ID_VALUE = "1";


  IngredientToIngredientCommand converter;

  @BeforeEach
  public void setUp() throws Exception {
    converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
  }

  @Test
  public void testNullConvert() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new Ingredient()));
  }

  @Test
  public void testConvertNullUOM() throws Exception {
    //given
    Ingredient ingredient = new Ingredient();
    ingredient.setId(ID_VALUE);
    ingredient.setAmount(AMOUNT);
    ingredient.setDescription(DESCRIPTION);
    ingredient.setUom(null);
    //when
    IngredientCommand ingredientCommand = converter.convert(ingredient);
    //then
    assertNull(ingredientCommand.getUom());
    assertEquals(ID_VALUE, ingredientCommand.getId());
    assertEquals(AMOUNT, ingredientCommand.getAmount());
    assertEquals(DESCRIPTION, ingredientCommand.getDescription());
  }

  @Test
  public void testConvertWithUom() throws Exception {
    //given
    Ingredient ingredient = new Ingredient();
    ingredient.setId(ID_VALUE);
    ingredient.setAmount(AMOUNT);
    ingredient.setDescription(DESCRIPTION);

    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(UOM_ID);

    ingredient.setUom(uom);
    //when
    IngredientCommand ingredientCommand = converter.convert(ingredient);
    //then
    assertEquals(ID_VALUE, ingredientCommand.getId());
    assertNotNull(ingredientCommand.getUom());
    assertEquals(UOM_ID, ingredientCommand.getUom().getId());
    // assertEquals(RECIPE, ingredientCommand.get);
    assertEquals(AMOUNT, ingredientCommand.getAmount());
    assertEquals(DESCRIPTION, ingredientCommand.getDescription());
  }
}