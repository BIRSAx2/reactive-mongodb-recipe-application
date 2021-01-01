package dev.mouhieddine.recipeapplication.converters;

import dev.mouhieddine.recipeapplication.commands.NotesCommand;
import dev.mouhieddine.recipeapplication.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NotesCommandToNotesTest {

  public static final String ID_VALUE = "1";
  public static final String RECIPE_NOTES = "Notes";
  NotesCommandToNotes converter;

  @BeforeEach
  public void setUp() throws Exception {
    converter = new NotesCommandToNotes();

  }

  @Test
  public void testNullParameter() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObject() throws Exception {
    assertNotNull(converter.convert(new NotesCommand()));
  }

  @Test
  public void convert() throws Exception {
    //given
    NotesCommand notesCommand = new NotesCommand();
    notesCommand.setId(ID_VALUE);
    notesCommand.setRecipeNotes(RECIPE_NOTES);

    //when
    Notes notes = converter.convert(notesCommand);

    //then
    assertNotNull(notes);
    assertEquals(ID_VALUE, notes.getId());
    assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
  }

}