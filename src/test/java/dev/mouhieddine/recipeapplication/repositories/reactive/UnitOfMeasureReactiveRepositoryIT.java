package dev.mouhieddine.recipeapplication.repositories.reactive;

import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Mouhieddine.dev
 * @since : 1/2/2021, Saturday
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UnitOfMeasureReactiveRepositoryIT {
  public static final String EACH = "Each";

  @Autowired
  UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

  @BeforeEach
  public void setUp() throws Exception {
    unitOfMeasureReactiveRepository.deleteAll().block();
  }

  @Test
  public void testSaveUom() throws Exception {
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setDescription(EACH);

    unitOfMeasureReactiveRepository.save(uom).block();

    Long count = unitOfMeasureReactiveRepository.count().block();

    assertEquals(Long.valueOf(1L), count);

  }

  @Test
  public void testFindByDescription() throws Exception {
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setDescription(EACH);

    unitOfMeasureReactiveRepository.save(uom).block();

    UnitOfMeasure fetchedUOM = unitOfMeasureReactiveRepository.findByDescription(EACH).block();

    assertEquals(EACH, fetchedUOM.getDescription());

  }
}