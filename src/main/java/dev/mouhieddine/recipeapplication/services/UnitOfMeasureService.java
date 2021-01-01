package dev.mouhieddine.recipeapplication.services;

import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
public interface UnitOfMeasureService {
  Set<UnitOfMeasureCommand> listAllUoms();
}
