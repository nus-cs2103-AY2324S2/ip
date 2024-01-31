public class Parser {

  /**
   * Parses the user input and returns the corresponding command.
   *
   * @param input The user input.
   * @return The corresponding command.
   */
  public static Command parse(String input) {
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
    } else {
      return new InvalidCommand(tokens);
    }
  }
}
