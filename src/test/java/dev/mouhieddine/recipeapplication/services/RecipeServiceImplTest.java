package dev.mouhieddine.recipeapplication.services;


import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.converters.RecipeCommandToRecipe;
import dev.mouhieddine.recipeapplication.converters.RecipeToRecipeCommand;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.repositories.reactive.RecipeReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
  @InjectMocks
  RecipeServiceImpl recipeService;

  @Mock
  RecipeReactiveRepository recipeReactiveRepository;
  @Mock
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock
  RecipeCommandToRecipe recipeCommandToRecipe;

  @BeforeEach
  public void setUp() throws Exception {

  }

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId("1");

    when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

    Recipe recipeReturned = recipeService.findById("1").block();

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeReactiveRepository, times(1)).findById(anyString());
    verify(recipeReactiveRepository, never()).findAll();
  }

  @Test
  public void getRecipeCommandByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId("1");

    when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId("1");

    when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

    RecipeCommand commandById = recipeService.findCommandById("1").block();

    assertNotNull("Null recipe returned", commandById);
    verify(recipeReactiveRepository, times(1)).findById(anyString());
    verify(recipeReactiveRepository, never()).findAll();
  }

  @Test
  public void getRecipesTest() throws Exception {

    when(recipeService.getRecipes()).thenReturn(Flux.just(new Recipe()));

    Flux<Recipe> recipes = recipeService.getRecipes();

    assertEquals(recipes.count().block(), 1);
    verify(recipeReactiveRepository, times(1)).findAll();
    verify(recipeReactiveRepository, never()).findById(anyString());
  }

  @Test
  public void testDeleteById() throws Exception {

    //given
    String idToDelete = "2";

    //when
    recipeService.deleteById(idToDelete);

    //no 'when', since method has void return type

    //then
    verify(recipeReactiveRepository, times(1)).deleteById(anyString());
  }
}