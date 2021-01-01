package dev.mouhieddine.recipeapplication.repositories;


import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ExtendWith(MockitoExtension.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @BeforeEach
  public void setUp() throws Exception {
  }

  @Test
  public void findByDescription() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

    assertEquals("Teaspoon", uomOptional.get().getDescription());
  }

  @Test
  public void findByDescriptionCup() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

    assertEquals("Cup", uomOptional.get().getDescription());
  }

}