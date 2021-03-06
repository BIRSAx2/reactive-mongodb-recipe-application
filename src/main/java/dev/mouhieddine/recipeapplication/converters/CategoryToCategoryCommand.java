package dev.mouhieddine.recipeapplication.converters;

import dev.mouhieddine.recipeapplication.commands.CategoryCommand;
import dev.mouhieddine.recipeapplication.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

  @Synchronized
  @Nullable
  @Override
  public CategoryCommand convert(Category source) {
    if (source == null) {
      return null;
    }

    final CategoryCommand categoryCommand = new CategoryCommand();

    categoryCommand.setId(source.getId());
    categoryCommand.setDescription(source.getDescription());

    return categoryCommand;
  }
}