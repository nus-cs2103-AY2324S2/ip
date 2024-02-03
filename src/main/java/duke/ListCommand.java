package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

public class ListCommand extends Command{

  @Override
  public void execute(TaskList taskList, Ui ui, String userInput) {
    List<Task> tasks = taskList.getTasks(); // Use duke.task.TaskList to get the list of tasks
        ui.showTasks(tasks);
  }
}
