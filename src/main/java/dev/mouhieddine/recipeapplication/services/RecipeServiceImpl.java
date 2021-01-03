package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.converters.RecipeCommandToRecipe;
import dev.mouhieddine.recipeapplication.converters.RecipeToRecipeCommand;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
  private final RecipeReactiveRepository recipeReactiveRepository;
  private final RecipeCommandToRecipe recipeCommandToRecipe;
  private final RecipeToRecipeCommand recipeToRecipeCommand;

  public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
    this.recipeReactiveRepository = recipeReactiveRepository;
    this.recipeCommandToRecipe = recipeCommandToRecipe;
    this.recipeToRecipeCommand = recipeToRecipeCommand;
  }

  @Override
  public Flux<Recipe> getRecipes() {
    return recipeReactiveRepository.findAll();
  }

  @Override
  public Mono<Recipe> findById(String id) {
    return recipeReactiveRepository.findById(id);
  }

  @Override
  public Mono<RecipeCommand> findCommandById(String id) {

    return recipeReactiveRepository.findById(id)
            .map(recipe -> {
              RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
              recipeCommand.getIngredients()
                      .forEach(ingredientCommand -> ingredientCommand.setRecipeId(recipeCommand.getId()));
              return recipeCommand;
            });
  }

  @Override
  public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
    return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
            .map(recipeToRecipeCommand::convert);
  }

  @Override
  public Mono<Void> deleteById(String idToDelete) {
    return recipeReactiveRepository.deleteById(idToDelete);
  }
}
