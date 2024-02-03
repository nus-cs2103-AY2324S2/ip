package duke;

import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command{

  @Override
  public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
    int taskIndex = Parser.extractTaskIndex(userInput);
    Task removedTask = taskList.deleteTask(taskIndex); // Use duke.task.TaskList to delete task
    if (removedTask != null) {
        ui.showTaskDeleted(removedTask, taskList.getSize());
    } else {
        throw new DukeException("Invalid task number. Please try again.");
    }
  }
  
}
