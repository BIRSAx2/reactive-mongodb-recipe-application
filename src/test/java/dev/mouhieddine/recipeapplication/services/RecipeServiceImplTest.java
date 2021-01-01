package dev.mouhieddine.recipeapplication.services;


import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.converters.RecipeCommandToRecipe;
import dev.mouhieddine.recipeapplication.converters.RecipeToRecipeCommand;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.exceptions.NotFoundException;
import dev.mouhieddine.recipeapplication.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
  @InjectMocks
  RecipeServiceImpl recipeService;

  @Mock
  RecipeRepository recipeRepository;

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
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById("1");

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyString());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void getRecipeByIdTestNotFound() throws Exception {

    Optional<Recipe> recipeOptional = Optional.empty();

    when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);


    assertThrows(NotFoundException.class, () -> {
      Recipe recipeReturned = recipeService.findById("1");
    });

  }

  @Test
  public void getRecipeCommandByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId("1");
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId("1");

    when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

    RecipeCommand commandById = recipeService.findCommandById("1");

    assertNotNull("Null recipe returned", commandById);
    verify(recipeRepository, times(1)).findById(anyString());
    verify(recipeRepository, never()).findAll();
  }

  @Test
  public void getRecipesTest() throws Exception {

    Recipe recipe = new Recipe();
    HashSet receipesData = new HashSet();
    receipesData.add(recipe);

    when(recipeService.getRecipes()).thenReturn(receipesData);

    Set<Recipe> recipes = recipeService.getRecipes();

    assertEquals(recipes.size(), 1);
    verify(recipeRepository, times(1)).findAll();
    verify(recipeRepository, never()).findById(anyString());
  }

  @Test
  public void testDeleteById() throws Exception {

    //given
    String idToDelete = "2";

    //when
    recipeService.deleteById(idToDelete);

    //no 'when', since method has void return type

    //then
    verify(recipeRepository, times(1)).deleteById(anyString());
  }
}