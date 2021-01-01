package dev.mouhieddine.recipeapplication.commands;

import dev.mouhieddine.recipeapplication.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
  private String id;

  @NotEmpty
  @Size(min = 3, max = 255)
  private String description;

  @Min(1)
  @Max(999)
  private Integer prepTime;

  @Min(1)
  @Max(999)
  private Integer cookTime;

  @Min(1)
  @Max(100)
  private Integer servings;
  private String source;

  @URL
  private String url;

  @NotEmpty
  private String directions;

  private List<IngredientCommand> ingredients = new ArrayList<>();
  private Byte[] image;
  private Difficulty difficulty;
  private NotesCommand notes;
  private List<CategoryCommand> categories = new ArrayList<>();
}