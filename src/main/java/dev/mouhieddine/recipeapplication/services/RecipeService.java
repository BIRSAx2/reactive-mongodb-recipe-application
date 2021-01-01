package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.domain.Recipe;

import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface RecipeService {
  Set<Recipe> getRecipes();

  Recipe findById(String id);

  RecipeCommand findCommandById(String id);

  RecipeCommand saveRecipeCommand(RecipeCommand command);

  void deleteById(String idToDelete);
}
