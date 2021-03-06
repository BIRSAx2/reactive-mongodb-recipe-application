package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.commands.IngredientCommand;
import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.services.IngredientService;
import dev.mouhieddine.recipeapplication.services.RecipeService;
import dev.mouhieddine.recipeapplication.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/

@Disabled
@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

  @Mock
  IngredientService ingredientService;

  @Mock
  UnitOfMeasureService unitOfMeasureService;

  @Mock
  RecipeService recipeService;
  @InjectMocks
  IngredientController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {

    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testListIngredients() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    when(recipeService.findCommandById(anyString())).thenReturn(Mono.just(recipeCommand));

    //when
    mockMvc.perform(get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/list"))
            .andExpect(model().attributeExists("recipe"));

    //then
    verify(recipeService, times(1)).findCommandById(anyString());
  }

  @Test
  public void testShowIngredient() throws Exception {
    //given
    Mono<IngredientCommand> ingredientCommand = Mono.just(new IngredientCommand());

    //when
    when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).thenReturn(ingredientCommand);

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/show"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/show"))
            .andExpect(model().attributeExists("ingredient"));
  }

  @Test
  public void testNewIngredientForm() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId("1");

    //when
    when(recipeService.findCommandById(anyString())).thenReturn(Mono.just(recipeCommand));
    when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasureCommand()));

    //then
    mockMvc.perform(get("/recipe/1/ingredient/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/ingredientform"))
            .andExpect(model().attributeExists("ingredient"))
            .andExpect(model().attributeExists("uomList"));

    verify(recipeService, times(1)).findCommandById(anyString());

  }

  @Test
  public void testUpdateIngredientForm() throws Exception {
    //given
    Mono<IngredientCommand> ingredientCommand = Mono.just(new IngredientCommand());

    //when
    when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString())).thenReturn(ingredientCommand);
    when(unitOfMeasureService.listAllUoms()).thenReturn(Flux.just(new UnitOfMeasureCommand()));

    //then
    mockMvc.perform(get("/recipe/1/ingredient/2/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/ingredientform"))
            .andExpect(model().attributeExists("ingredient"))
            .andExpect(model().attributeExists("uomList"));
  }

  @Test
  public void testSaveOrUpdate() throws Exception {
    //given
    IngredientCommand command = new IngredientCommand();
    command.setId("3");
    command.setRecipeId("2");
    Mono<IngredientCommand> commandMono = Mono.just(command);

    //when
    when(ingredientService.saveIngredientCommand(any())).thenReturn(commandMono);

    //then
    mockMvc.perform(post("/recipe/2/ingredient")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "")
            .param("description", "some string")
    )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/2/ingredients"));

  }

  @Test
  public void testDeleteIngredient() throws Exception {

    when(ingredientService.deleteById(anyString(), anyString())).thenReturn(Mono.empty());

    //then
    mockMvc.perform(get("/recipe/2/ingredient/3/delete")
    )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/2/ingredients"));

    verify(ingredientService, times(1)).deleteById(anyString(), anyString());


  }
}