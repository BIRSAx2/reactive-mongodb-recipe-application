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
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

  @Synchronized
  @Nullable
  @Override
  public UnitOfMeasure convert(UnitOfMeasureCommand source) {
    if (source == null) {
      return null;
    }

    final UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(source.getId());
    uom.setDescription(source.getDescription());
    return uom;
  }
}