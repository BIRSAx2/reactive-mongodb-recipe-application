package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import dev.mouhieddine.recipeapplication.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitOfMeasureServiceImplTest {

  UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
  UnitOfMeasureService service;

  @Mock
  UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  public void setUp() throws Exception {

    service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
  }

  @Test
  public void listAllUoms() throws Exception {
    //given
    Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
    UnitOfMeasure uom1 = new UnitOfMeasure();
    uom1.setId("1");
    unitOfMeasures.add(uom1);

    UnitOfMeasure uom2 = new UnitOfMeasure();
    uom2.setId("2");
    unitOfMeasures.add(uom2);

    when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

    //when
    Set<UnitOfMeasureCommand> commands = service.listAllUoms();

    //then
    assertEquals(2, commands.size());
    verify(unitOfMeasureRepository, times(1)).findAll();
  }

}