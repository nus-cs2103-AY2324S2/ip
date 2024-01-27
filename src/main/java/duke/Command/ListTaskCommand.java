package duke.Command;

import task.Task;
import task.TaskManager;

import java.sql.SQLException;

public class ListTaskCommand extends Command {
  public static final String COMMAND_WORD = "list";

  @Override
  public String execute(TaskManager tm) {
    try {
      if (tm.getNumberOfTasks() == 0) {
        return "No tasks added yet!\n";
      }
      StringBuilder sb = new StringBuilder();
      for (Task task :tm.getTasks()) {
        sb.append(String.format("%d. %s\n", task.taskID, task));
      }
      return sb.toString();
    } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
