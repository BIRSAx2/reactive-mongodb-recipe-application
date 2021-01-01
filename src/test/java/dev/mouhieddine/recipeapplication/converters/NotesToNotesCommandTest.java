package dev.mouhieddine.recipeapplication.converters;

import dev.mouhieddine.recipeapplication.commands.NotesCommand;
import dev.mouhieddine.recipeapplication.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NotesToNotesCommandTest {

  public static final String ID_VALUE = "1";
  public static final String RECIPE_NOTES = "Notes";
  NotesToNotesCommand converter;

  @BeforeEach
  public void setUp() throws Exception {
    converter = new NotesToNotesCommand();
  }

  @Test
  public void convert() throws Exception {
    //given
    Notes notes = new Notes();
    notes.setId(ID_VALUE);
    notes.setRecipeNotes(RECIPE_NOTES);

    //when
    NotesCommand notesCommand = converter.convert(notes);

    //then
    assertEquals(ID_VALUE, notesCommand.getId());
    assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
  }

  @Test
  public void testNull() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new Notes()));
  }
}