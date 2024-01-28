package duke.command;

import database.TaskORM;

import java.sql.SQLException;

public class DeleteTaskCommand extends Command{
  public static final String COMMAND_WORD = "delete";
  private final int taskID;
  public DeleteTaskCommand(int taskID) {
    this.taskID = taskID;
  }

  @Override
  public String execute(TaskORM tm) {
    try {
      task.Task task = tm.delete(taskID);
      return "Noted. I've removed this task:\n"
        + "  " + task + "\n"
        + String.format("Now you have %d tasks in the list.\n", tm.count());
      } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
