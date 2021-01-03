package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.IngredientCommand;
import reactor.core.publisher.Mono;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface IngredientService {
  Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

  Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);

  Mono<Void> deleteById(String recipeId, String idToDelete);
}
