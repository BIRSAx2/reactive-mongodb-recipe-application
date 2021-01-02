package dev.mouhieddine.recipeapplication.repositories.reactive;

import dev.mouhieddine.recipeapplication.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author : Mouhieddine.dev
 * @since : 1/2/2021, Saturday
 **/
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
