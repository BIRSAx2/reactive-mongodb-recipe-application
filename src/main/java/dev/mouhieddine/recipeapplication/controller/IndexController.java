package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Slf4j
@Controller
public class IndexController {

  private final RecipeService recipeService;

  public IndexController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping({"", "/", "/index"})
  public String getIndexPage(Model model) {
    log.debug("Getting Index page");

    model.addAttribute("recipes", recipeService.getRecipes());

    return "index";
  }
}