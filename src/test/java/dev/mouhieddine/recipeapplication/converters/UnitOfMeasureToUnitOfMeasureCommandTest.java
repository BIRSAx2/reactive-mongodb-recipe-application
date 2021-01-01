package dev.mouhieddine.recipeapplication.converters;

import dev.mouhieddine.recipeapplication.commands.UnitOfMeasureCommand;
import dev.mouhieddine.recipeapplication.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UnitOfMeasureToUnitOfMeasureCommandTest {

  public static final String DESCRIPTION = "description";
  public static final String LONG_VALUE = "1";

  UnitOfMeasureToUnitOfMeasureCommand converter;

  @BeforeEach
  public void setUp() throws Exception {
    converter = new UnitOfMeasureToUnitOfMeasureCommand();
  }

  @Test
  public void testNullObjectConvert() throws Exception {
    assertNull(converter.convert(null));
  }

  @Test
  public void testEmptyObj() throws Exception {
    assertNotNull(converter.convert(new UnitOfMeasure()));
  }

  @Test
  public void convert() throws Exception {
    //given
    UnitOfMeasure uom = new UnitOfMeasure();
    uom.setId(LONG_VALUE);
    uom.setDescription(DESCRIPTION);
    //when
    UnitOfMeasureCommand uomc = converter.convert(uom);

    //then
    assertEquals(LONG_VALUE, uomc.getId());
    assertEquals(DESCRIPTION, uomc.getDescription());
  }

}