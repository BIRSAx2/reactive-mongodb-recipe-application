package dev.mouhieddine.recipeapplication.repositories.reactive;

import dev.mouhieddine.recipeapplication.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Mouhieddine.dev
 * @since : 1/2/2021, Saturday
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryReactiveRepositoryIT {
  @Autowired
  CategoryReactiveRepository categoryReactiveRepository;

  @BeforeEach
  public void setUp() throws Exception {
    categoryReactiveRepository.deleteAll().block();
  }

  @Test
  public void testSave() throws Exception {
    Category category = new Category();
    category.setDescription("Foo");

    categoryReactiveRepository.save(category).block();

    Long count = categoryReactiveRepository.count().block();

    assertEquals(Long.valueOf(1L), count);
  }

  @Test
  public void testFindByDescription() throws Exception {
    Category category = new Category();
    category.setDescription("Foo");

    categoryReactiveRepository.save(category).then().block();

    Category fetchedCat = categoryReactiveRepository.findByDescription("Foo").block();

    assertNotNull(fetchedCat.getId());
  }
}