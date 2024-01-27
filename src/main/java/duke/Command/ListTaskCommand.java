package duke.Command;

import database.TaskORM;

import java.sql.SQLException;

public class ListTaskCommand extends Command {
  public static final String COMMAND_WORD = "list";

  @Override
  public String execute(TaskORM tm) {
    try {
      if (tm.count() == 0) {
        return "No tasks added yet!\n";
      }
      StringBuilder sb = new StringBuilder();
      for (task.Task task :tm.list()) {
        sb.append(String.format("%d. %s\n", task.taskID, task));
      }
      return sb.toString();
    } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
