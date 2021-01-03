package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.converters.RecipeCommandToRecipe;
import dev.mouhieddine.recipeapplication.converters.RecipeToRecipeCommand;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    RecipeCommand recipeCommand = recipeToRecipeCommand.convert(findById(id).block());

    //enhance command object with id value
    if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
      recipeCommand.getIngredients().forEach(rc -> {
        rc.setRecipeId(recipeCommand.getId());
      });
    }

    return Mono.just(recipeCommand);
  }

  @Override
  public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
    Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

    Recipe savedRecipe = recipeReactiveRepository.save(detachedRecipe).block();
    log.debug("Saved RecipeId:" + savedRecipe.getId());
    return Mono.just(recipeToRecipeCommand.convert(savedRecipe));
  }

  @Override
  public Mono<Void> deleteById(String idToDelete) {
    recipeReactiveRepository.deleteById(idToDelete);
    return Mono.empty();
  }
}
