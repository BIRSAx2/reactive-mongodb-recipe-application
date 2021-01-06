package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.config.WebConfig;
import dev.mouhieddine.recipeapplication.domain.Recipe;
import dev.mouhieddine.recipeapplication.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

/**
 * @author : Mouhieddine.dev
 * @since : 1/6/2021, Wednesday
 **/
@ExtendWith(MockitoExtension.class)
public class RouterFunctionTest {

  WebTestClient webTestClient;
  @Mock
  RecipeService recipeService;

  @BeforeEach
  void setUp() {
    WebConfig webConfig = new WebConfig();

    RouterFunction<?> routerFunction = webConfig.routes(recipeService);

    webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
  }

  @Test
  void testGetRecipes() {
    when(recipeService.getRecipes()).thenReturn(Flux.just());

    webTestClient.get().uri("/api/recipes")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
  }
  @Test
  public void testGetRecipesWithData() throws Exception {

    when(recipeService.getRecipes()).thenReturn(Flux.just(new Recipe(), new Recipe()));

    webTestClient.get().uri("/api/recipes")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Recipe.class);
  }
}
