package duke.command;

import duke.Parser;

public abstract class Command {
  public static Command interpret(String input) {
    Parser parser = new Parser(input);

    switch (parser.parseCommand()) {
    case AddTaskCommand.ADD_TODO_COMMAND:
      // Fallthrough
    case AddTaskCommand.ADD_DEADLINE_COMMAND:
      // Fallthrough
    case AddTaskCommand.ADD_EVENT_COMMAND:
      return new AddTaskCommand(parser.parseCommand(), parser.parseArguments());
    case ListTaskCommand.COMMAND_WORD:
      return new ListTaskCommand();
    case MarkTaskCommand.COMMAND_WORD:
      return new MarkTaskCommand(parser.parseTaskID());
    case UnmarkTaskCommand.COMMAND_WORD:
      return new UnmarkTaskCommand(parser.parseTaskID());
    case DeleteTaskCommand.COMMAND_WORD:
      return new DeleteTaskCommand(parser.parseTaskID());
    case FindTaskCommand.COMMAND_WORD:
      return new FindTaskCommand(parser.parseArguments());
    case ExitCommand.COMMAND_WORD:
      return new ExitCommand();
    default:
      return new UnknownCommand(parser.parseCommand());
    }
  }

  /**
   * Executes the command.
   *
   * @return the output of the command that will be printed to the user
   */
  public abstract String execute();

  /**
   * Checks if the command is a termination command.
   *
   * @return true if the command is a termination command, false otherwise
   */
  public boolean terminate() {
    return false;
  }
}
