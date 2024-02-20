package morty;

import morty.command.Command;
import morty.command.DeadlineCommand;
import morty.command.DeleteCommand;
import morty.command.DoneCommand;
import morty.command.EventCommand;
import morty.command.ExitCommand;
import morty.command.FindCommand;
import morty.command.InvalidCommand;
import morty.command.ListCommand;
import morty.command.TodoCommand;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {

  /**
   * Parses the user input and returns the corresponding command.
   *
   * @param input The user input.
   * @return The corresponding command.
   */
  public static Command parse(String input) {
    assert input != null : "Input should not be null";
    String[] tokens = input.split(" ", 2);
    switch (tokens[0]) {
    case "bye":
      return new ExitCommand(tokens);
    case "list":
      return new ListCommand(tokens);
    case "done":
      return new DoneCommand(tokens);
    case "delete":
      return new DeleteCommand(tokens);
    case "todo":
      return new TodoCommand(tokens);
    case "deadline":
      return new DeadlineCommand(tokens);
    case "event":
      return new EventCommand(tokens);
    case "find":
      return new FindCommand(tokens);
    default:
      return new InvalidCommand(tokens);
    }
  }
}
