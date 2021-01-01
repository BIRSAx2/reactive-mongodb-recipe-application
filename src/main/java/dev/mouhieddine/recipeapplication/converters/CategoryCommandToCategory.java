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
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

  @Synchronized
  @Nullable
  @Override
  public Category convert(CategoryCommand source) {
    if (source == null) {
      return null;
    }

    final Category category = new Category();
    category.setId(source.getId());
    category.setDescription(source.getDescription());
    return category;
  }
}
