package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface UnitOfMeasureService {
  Flux<UnitOfMeasureCommand> listAllUoms();
}
