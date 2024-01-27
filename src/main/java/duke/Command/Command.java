package duke.Command;

import duke.Parser;
import task.TaskManager;

public abstract class Command {
  public static Command Interpret(String input) {
    Parser parser = new Parser(input);

    switch (parser.command) {
      case ListTaskCommand.COMMAND_WORD:
        return new ListTaskCommand();
      case MarkTaskCommand.COMMAND_WORD:
        return new MarkTaskCommand(parser.parseTaskID());
      case UnmarkTaskCommand.COMMAND_WORD:
        return new UnmarkTaskCommand(parser.parseTaskID());
      case DeleteTaskCommand.COMMAND_WORD:
        return new DeleteTaskCommand(parser.parseTaskID());
      case AddTaskCommand.ADD_TODO_COMMAND:
      case AddTaskCommand.ADD_DEADLINE_COMMAND:
      case AddTaskCommand.ADD_EVENT_COMMAND:
        return new AddTaskCommand(parser.command, parser.arguments);
      case ExitCommand.COMMAND_WORD:
        return new ExitCommand();
      default:
        return new UnknownCommand(parser.command);
    }
  }

  /**
   * Executes the command.
   *
   * @return the output of the command that will be printed to the user
   */
  public abstract String execute(TaskManager tm);

  /**
   * Checks if the command is a termination command.
   *
   * @return true if the command is a termination command, false otherwise
   */
  public boolean terminate() {
    return false;
  }
}
