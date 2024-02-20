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
    if (tokens[0].equals("bye")) {
      return new ExitCommand(tokens);
    } else if (tokens[0].equals("list")) {
      return new ListCommand(tokens);
    } else if (tokens[0].equals("done")) {
      return new DoneCommand(tokens);
    } else if (tokens[0].equals("delete")) {
      return new DeleteCommand(tokens);
    } else if (tokens[0].equals("todo")) {
      return new TodoCommand(tokens);
    } else if (tokens[0].equals("deadline")) {
      return new DeadlineCommand(tokens);
    } else if (tokens[0].equals("event")) {
      return new EventCommand(tokens);
    } else if (tokens[0].equals("find")) {
      return new FindCommand(tokens);
    } else {
      return new InvalidCommand(tokens);
    }
  }
}
