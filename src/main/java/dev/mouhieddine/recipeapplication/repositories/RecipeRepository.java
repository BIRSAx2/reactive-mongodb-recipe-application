package dev.mouhieddine.recipeapplication.repositories;

import dev.mouhieddine.recipeapplication.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
