package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.converters.RecipeCommandToRecipe;
import dev.mouhieddine.recipeapplication.converters.RecipeToRecipeCommand;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {

  public static final String NEW_DESCRIPTION = "New Description";

  @Autowired
  RecipeService recipeService;

  @Autowired
  RecipeRepository recipeRepository;

  @Autowired
  RecipeCommandToRecipe recipeCommandToRecipe;

  @Autowired
  RecipeToRecipeCommand recipeToRecipeCommand;

  //  @Transactional
  @Test
  public void testSaveOfDescription() throws Exception {
    //given
    Iterable<Recipe> recipes = recipeRepository.findAll();
    Recipe testRecipe = recipes.iterator().next();
    RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

    //when
    testRecipeCommand.setDescription(NEW_DESCRIPTION);
    RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand).block();

    //then
    assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
    assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
    assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
    assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
  }
}
