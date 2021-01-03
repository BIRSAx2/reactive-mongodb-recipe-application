package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface RecipeService {
  Flux<Recipe> getRecipes();

  Mono<Recipe> findById(String id);

  Mono<RecipeCommand> findCommandById(String id);

  Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);

  Mono<Void> deleteById(String idToDelete);
}
