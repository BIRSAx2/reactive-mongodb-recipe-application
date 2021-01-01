package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.IngredientCommand;
import dev.mouhieddine.recipeapplication.converters.IngredientCommandToIngredient;
import dev.mouhieddine.recipeapplication.converters.IngredientToIngredientCommand;
import dev.mouhieddine.recipeapplication.converters.UnitOfMeasureCommandToUnitOfMeasure;
import dev.mouhieddine.recipeapplication.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.domain.Ingredient;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.repositories.RecipeRepository;
import dev.mouhieddine.recipeapplication.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;
  private final IngredientCommandToIngredient ingredientCommandToIngredient;

  @Mock
  RecipeRepository recipeRepository;

  @Mock
  UnitOfMeasureRepository unitOfMeasureRepository;

  IngredientService ingredientService;

  //init converters
  public IngredientServiceImplTest() {
    this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
  }

  @BeforeEach
  public void setUp() throws Exception {

    ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
            recipeRepository, unitOfMeasureRepository);
  }

  @Test
  public void findByRecipeIdAndId() throws Exception {
  }

  @Test
  public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
    //given
    Recipe recipe = new Recipe();
    recipe.setId("1");

    Ingredient ingredient1 = new Ingredient();
    ingredient1.setId("1");

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId("1");

    Ingredient ingredient3 = new Ingredient();
    ingredient3.setId("3");

    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

    //then
    IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3");

    //when
    assertEquals("3", ingredientCommand.getId());
    verify(recipeRepository, times(1)).findById(anyString());
  }


  @Test
  public void testSaveRecipeCommand() throws Exception {
    //given
    IngredientCommand command = new IngredientCommand();
    command.setId("3");
    command.setRecipeId("2");

    Optional<Recipe> recipeOptional = Optional.of(new Recipe());

    Recipe savedRecipe = new Recipe();
    savedRecipe.addIngredient(new Ingredient());
    savedRecipe.getIngredients().iterator().next().setId("3");

    when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
    when(recipeRepository.save(any())).thenReturn(savedRecipe);

    //when
    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

    //then
    assertEquals("3", savedCommand.getId());
    verify(recipeRepository, times(1)).findById(anyString());
    verify(recipeRepository, times(1)).save(any(Recipe.class));

  }

  @Test
  public void testDeleteById() throws Exception {
    //given
    Recipe recipe = new Recipe();
    Ingredient ingredient = new Ingredient();
    ingredient.setId("3");
    recipe.addIngredient(ingredient);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

    //when
    ingredientService.deleteById("1", "3");

    //then
    verify(recipeRepository, times(1)).findById(anyString());
    verify(recipeRepository, times(1)).save(any(Recipe.class));
  }
}