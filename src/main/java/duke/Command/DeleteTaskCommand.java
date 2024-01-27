package duke.Command;

import task.Task;
import task.TaskManager;

import java.sql.SQLException;

public class DeleteTaskCommand extends Command{
  public static final String COMMAND_WORD = "delete";
  private final int taskID;
  public DeleteTaskCommand(int taskID) {
    this.taskID = taskID;
  }

  @Override
  public String execute(TaskManager tm) {
    try {
      Task task = tm.deleteTask(taskID);
      return "Noted. I've removed this task:\n"
        + "  " + task + "\n"
        + String.format("Now you have %d tasks in the list.\n", tm.getNumberOfTasks());
      } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
