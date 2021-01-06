package dev.mouhieddine.recipeapplication.controller;

import dev.mouhieddine.recipeapplication.commands.IngredientCommand;
import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.services.IngredientService;
import dev.mouhieddine.recipeapplication.services.RecipeService;
import dev.mouhieddine.recipeapplication.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Slf4j
@Controller
public class IngredientController {

  private final IngredientService ingredientService;
  private final RecipeService recipeService;
  private final UnitOfMeasureService unitOfMeasureService;
  private WebDataBinder webDataBinder;

  public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
    this.ingredientService = ingredientService;
    this.recipeService = recipeService;
    this.unitOfMeasureService = unitOfMeasureService;
  }

  @InitBinder("ingredient")
  public void initBinder(WebDataBinder webDataBinder) {
    this.webDataBinder = webDataBinder;
  }

  @ModelAttribute("uomList")
  public Flux<UnitOfMeasureCommand> populateUomList() {
    return unitOfMeasureService.listAllUoms();
  }

  @GetMapping("/recipe/{recipeId}/ingredients")
  public String listIngredients(@PathVariable String recipeId, Model model) {
    log.debug("Getting ingredient list for recipe id: " + recipeId);

    model.addAttribute("recipe", recipeService.findCommandById(recipeId));

    return "recipe/ingredient/list";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
  public String showRecipeIngredient(@PathVariable String recipeId,
                                     @PathVariable String id, Model model) {
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
    return "recipe/ingredient/show";
  }

  @GetMapping("recipe/{recipeId}/ingredient/new")
  public String newIngredient(@PathVariable String recipeId, Model model) {
    //make sure we have a good id value
//    RecipeCommand recipeCommand = recipeService.findCommandById(recipeId).block();
    //todo raise exception if null

    //need to return back parent id for hidden form property
    IngredientCommand ingredientCommand = new IngredientCommand();
    model.addAttribute("ingredient", ingredientCommand);

    //init uom
    ingredientCommand.setUom(new UnitOfMeasureCommand());
    ingredientCommand.setRecipeId(recipeId);
    return "recipe/ingredient/ingredientform";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
  public String updateRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {
    model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

    return "recipe/ingredient/ingredientform";
  }

  @PostMapping("recipe/{recipeId}/ingredient")
  public String saveOrUpdate(@ModelAttribute("ingredient") IngredientCommand command, Model model, @PathVariable String recipeid) {
    webDataBinder.validate();
    BindingResult bindingResult = webDataBinder.getBindingResult();

    if (bindingResult.hasErrors()) {
      log.error("Binding error:");

      bindingResult.getAllErrors().forEach(objectError -> {
        log.error(objectError.toString());
      });

      return "recipe/ingredient/ingredientform";

    }
//    IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command).block();

    return "redirect:/recipe/" + recipeid + "/ingredients";
  }

  @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
  public String deleteIngredient(@PathVariable String recipeId,
                                 @PathVariable String id) {

    log.debug("deleting ingredient id:" + id);
    ingredientService.deleteById(recipeId, id).subscribe();

    return "redirect:/recipe/" + recipeId + "/ingredients";
  }


}