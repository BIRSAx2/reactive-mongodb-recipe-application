package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import dev.mouhieddine.recipeapplication.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitOfMeasureServiceImplTest {

  UnitOfMeasureToUnitOfMeasureCommand uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
  UnitOfMeasureService service;

  @Mock
  UnitOfMeasureReactiveRepository repository;

  @BeforeEach
  public void setUp() throws Exception {

    service = new UnitOfMeasureServiceImpl(repository, uomConverter);
  }

  @Test
  public void listAllUoms() throws Exception {
    //given
    UnitOfMeasure uom1 = new UnitOfMeasure();
    uom1.setId("1");

    UnitOfMeasure uom2 = new UnitOfMeasure();
    uom2.setId("2");

    when(repository.findAll()).thenReturn(Flux.just(uom1, uom2));

    //when
    Flux<UnitOfMeasureCommand> commands = service.listAllUoms();

    //then
    assertEquals(2, commands.count().block());
    verify(repository, times(1)).findAll();
  }

}