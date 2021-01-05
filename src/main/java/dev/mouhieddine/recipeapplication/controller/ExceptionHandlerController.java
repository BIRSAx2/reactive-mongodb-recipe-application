package dev.mouhieddine.recipeapplication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler(NumberFormatException.class)
//  public ModelAndView handleNumberFormat(Exception exception) {
//
//    log.error("Handling Number Format Exception");
//    log.error(exception.getMessage());
//
//    ModelAndView modelAndView = new ModelAndView();
//
//    modelAndView.setViewName("400error");
//    modelAndView.addObject("exception", exception);
//
//    return modelAndView;
//  }
}
