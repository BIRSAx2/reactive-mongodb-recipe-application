package dev.mouhieddine.recipeapplication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NumberFormatException.class)
  public String handleNumberFormat(Exception exception, Model model) {

    log.error("Handling Number Format Exception");
    log.error(exception.getMessage());


    model.addAttribute("exception", exception);

    return "400error";
  }
}
