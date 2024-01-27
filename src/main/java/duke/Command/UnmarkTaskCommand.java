package duke.Command;

import task.Task;
import task.TaskManager;

import java.sql.SQLException;

public class UnmarkTaskCommand extends Command{
  public static final String COMMAND_WORD = "unmark";
  private final int taskID;
  public UnmarkTaskCommand(int taskID) {
    this.taskID = taskID;
  }

  @Override
  public String execute(TaskManager tm) {
    try {
      tm.unmarkTaskAsDone(taskID);
    } catch (SQLException e) {
      return e.getMessage();
    }

    try {
      Task task = tm.getTask(taskID);
      return "Ok, I've marked this task as not done yet:\n" + "  " + task + "\n";
    } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
