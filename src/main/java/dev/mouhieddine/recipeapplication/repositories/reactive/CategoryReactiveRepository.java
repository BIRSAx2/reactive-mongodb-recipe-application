package dev.mouhieddine.recipeapplication.repositories.reactive;

import dev.mouhieddine.recipeapplication.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author : Mouhieddine.dev
 * @since : 1/2/2021, Saturday
 **/
public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
  Mono<Category> findByDescription(String description);
}
