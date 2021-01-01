package dev.mouhieddine.recipeapplication.converters;

import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

    if (unitOfMeasure != null) {
      final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
      uomc.setId(unitOfMeasure.getId());
      uomc.setDescription(unitOfMeasure.getDescription());
      return uomc;
    }
    return null;
  }
}
