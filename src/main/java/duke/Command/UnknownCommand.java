package duke.Command;

import task.TaskManager;

public class UnknownCommand extends Command {
  private final String command;
  public UnknownCommand(String command) {
    this.command = command;
  }

  @Override
  public String execute(TaskManager tm) {
    return String.format("I'm sorry, but I don't know what \"%s\" means.\n", this.command);
  }
}
