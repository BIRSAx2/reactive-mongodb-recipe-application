package dev.mouhieddine.recipeapplication.converters;

import dev.mouhieddine.recipeapplication.commands.NotesCommand;
import dev.mouhieddine.recipeapplication.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

  @Synchronized
  @Nullable
  @Override
  public Notes convert(NotesCommand source) {
    if (source == null) {
      return null;
    }

    final Notes notes = new Notes();
    notes.setId(source.getId());
    notes.setRecipeNotes(source.getRecipeNotes());
    return notes;
  }
}