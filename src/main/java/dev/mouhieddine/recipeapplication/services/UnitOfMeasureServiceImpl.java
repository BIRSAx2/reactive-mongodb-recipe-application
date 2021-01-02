package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

  private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepositoryRepository;
  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepositoryRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
    this.unitOfMeasureReactiveRepositoryRepository = unitOfMeasureReactiveRepositoryRepository;
    this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
  }

  @Override
  public Flux<UnitOfMeasureCommand> listAllUoms() {
//    return StreamSupport.stream(unitOfMeasureRepository.findAll()
//            .spliterator(), false)
//            .map(unitOfMeasureToUnitOfMeasureCommand::convert)
//            .collect(Collectors.toSet());

    return unitOfMeasureReactiveRepositoryRepository.findAll()
            .map(unitOfMeasureToUnitOfMeasureCommand::convert);
  }
}
