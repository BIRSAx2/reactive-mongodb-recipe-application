package dev.mouhieddine.recipeapplication.repositories;

import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String> {

  Optional<UnitOfMeasure> findByDescription(String description);
}
