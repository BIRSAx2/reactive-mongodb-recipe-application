package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.IngredientCommand;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface IngredientService {
  IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

  IngredientCommand saveIngredientCommand(IngredientCommand command);

  void deleteById(String recipeId, String idToDelete);
}
