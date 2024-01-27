package duke.Command;

import task.Task;
import task.TaskManager;

import java.sql.SQLException;

public class MarkTaskCommand extends Command{
  public static final String COMMAND_WORD = "mark";
  private final int taskID;
  public MarkTaskCommand(int taskID) {
    this.taskID = taskID;
  }

  @Override
  public String execute(TaskManager tm) {
    try {
      tm.markTaskAsDone(taskID);
    } catch (SQLException e) {
      return e.getMessage();
    }

    try {
      Task task = tm.getTask(taskID);
      return "Nice! I've marked this task as done:\n" + "  " + task + "\n";
    } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
