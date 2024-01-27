package duke.Command;

import task.TaskManager;

public class ExitCommand extends Command {
  public static final String COMMAND_WORD = "bye";

  @Override
  public String execute(TaskManager tm) {
    return "Bye. Hope to see you again soon!\n";
  }

  @Override
  public boolean terminate() {
    return true;
  }
}
