package dev.mouhieddine.recipeapplication.repositories;

import dev.mouhieddine.recipeapplication.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface CategoryRepository extends CrudRepository<Category, String> {
  Optional<Category> findByDescription(String description);
}
