package duke.command;

import java.sql.SQLException;

import database.TaskOrm;

public class ListTaskCommand extends Command {
  public static final String COMMAND_WORD = "list";

  @Override
  public String execute() {
    TaskOrm tm = new TaskOrm();
    try {
      if (tm.count() == 0) {
        return "No tasks added yet!\n";
      }
      StringBuilder sb = new StringBuilder();
      for (task.Task task : tm.list()) {
        sb.append(String.format("%d. %s\n", task.taskID, task));
      }
      return sb.toString();
    } catch (SQLException e) {
      return e.getMessage();
    }
  }
}
