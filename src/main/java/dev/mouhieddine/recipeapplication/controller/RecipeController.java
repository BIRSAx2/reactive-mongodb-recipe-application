package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.commands.RecipeCommand;
import dev.mouhieddine.recipeapplication.exceptions.NotFoundException;
import dev.mouhieddine.recipeapplication.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;
import reactor.core.publisher.Mono;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Slf4j
@Controller
public class RecipeController {

  private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
  private final RecipeService recipeService;
  private WebDataBinder webDataBinder;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/recipe/{id}/show")
  public String showById(@PathVariable String id, Model model) {

    model.addAttribute("recipe", recipeService.findById(id));

    return "recipe/show";
  }

  @InitBinder
  public void initBinder(WebDataBinder webDataBinder) {
    this.webDataBinder = webDataBinder;
  }

  @GetMapping("recipe/new")
  public String newRecipe(Model model) {
    model.addAttribute("recipe", new RecipeCommand());

    return "recipe/recipeform";
  }

  @GetMapping("recipe/{id}/update")
  public String updateRecipe(@PathVariable String id, Model model) {
    model.addAttribute("recipe", recipeService.findCommandById(id));
    return RECIPE_RECIPEFORM_URL;
  }

  @PostMapping("recipe")
  public Mono<String> saveOrUpdate(@ModelAttribute("recipe") Mono<RecipeCommand> command) {
    webDataBinder.validate();
    BindingResult bindingResult = webDataBinder.getBindingResult();

    if (bindingResult.hasErrors()) {

      bindingResult.getAllErrors().forEach(objectError -> {
        log.debug(objectError.toString());
      });
      return Mono.just(RECIPE_RECIPEFORM_URL);
    }
    return command.flatMap(
            recipeCommand -> recipeService.saveRecipeCommand(recipeCommand)
                    .flatMap(recipeSaved -> Mono.just("redirect:/recipe/" + recipeSaved.getId() + "/show"))
    );
  }

  @GetMapping("recipe/{id}/delete")
  public String deleteById(@PathVariable String id) {

    log.debug("Deleting id: " + id);

    recipeService.deleteById(id).subscribe();
    return "redirect:/";
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
  public String handleNotFound(Exception exception, Model model) {

    log.error("Handling not found exception");
    log.error(exception.getMessage());


    model.addAttribute("exception", exception);

    return "404error";
  }
}