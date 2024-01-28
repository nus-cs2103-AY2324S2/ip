package duke.command;

import java.sql.SQLException;

import database.TaskOrm;

public class UnmarkTaskCommand extends Command {
  public static final String COMMAND_WORD = "unmark";
  private final int taskID;

  public UnmarkTaskCommand(int taskID) {
    this.taskID = taskID;
  }

  @Override
  public String execute() {
    TaskOrm tm = new TaskOrm();
    try {
      tm.unmark(taskID);
      task.Task task = tm.get(taskID);
      return "Ok, I've marked this task as not done yet:\n" + "  " + task + "\n";
    } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
