package dev.mouhieddine.recipeapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Mouhieddine.dev
 * @since : 1/1/2021, Friday
 **/
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
  private String id;
  private String recipeNotes;

}
