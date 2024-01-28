package duke.command;

import database.TaskORM;

import java.sql.SQLException;

public class MarkTaskCommand extends Command{
  public static final String COMMAND_WORD = "mark";
  private final int taskID;
  public MarkTaskCommand(int taskID) {
    this.taskID = taskID;
  }

  @Override
  public String execute(TaskORM tm) {
    try {
      tm.mark(taskID);
      task.Task task = tm.get(taskID);
      return "Nice! I've marked this task as done:\n" + "  " + task + "\n";
    } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
