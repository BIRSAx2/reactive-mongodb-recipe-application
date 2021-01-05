package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/

@Disabled
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = IndexController.class)
@Import(RecipeService.class)
class IndexControllerTest {
  @MockBean
  RecipeService recipeService;
  @Autowired
  WebTestClient webTestClient;

  @Mock
  Model model;
  @InjectMocks
  IndexController controller;

  @BeforeEach
  public void setUp() throws Exception {

  }

  @Test
  public void testMockMVC() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    when(recipeService.getRecipes()).thenReturn(Flux.empty());

    mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
  }

  @Test
  public void getIndexPage() throws Exception {

    //given
    Recipe recipe = new Recipe();
    recipe.setId("1");

    when(recipeService.getRecipes()).thenReturn(Flux.just(recipe, new Recipe()));

    ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

    //when
    String viewName = controller.getIndexPage(model);


    //then
    assertEquals("index", viewName);
    verify(recipeService, times(1)).getRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
    List<Recipe> setInController = argumentCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}