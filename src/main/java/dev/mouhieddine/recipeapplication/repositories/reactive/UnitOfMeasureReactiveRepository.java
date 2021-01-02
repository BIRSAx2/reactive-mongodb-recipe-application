package dev.mouhieddine.recipeapplication.repositories.reactive;

import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author : Mouhieddine.dev
 * @since : 1/2/2021, Saturday
 **/
public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {
  Mono<UnitOfMeasure> findByDescription(String description);

}
