package duke.Command;

import database.TaskORM;

public class ExitCommand extends Command {
  public static final String COMMAND_WORD = "bye";

  @Override
  public String execute(TaskORM tm) {
    return "Bye. Hope to see you again soon!\n";
  }

  @Override
  public boolean terminate() {
    return true;
  }
}
