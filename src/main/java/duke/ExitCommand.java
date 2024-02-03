package duke;

import duke.task.TaskList;

public class ExitCommand extends Command {

  @Override
  public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
    ui.showGoodbye(); // Use the duke.Ui class to display a farewell message
    Storage.saveTasks(taskList.getTasks()); // Use duke.task.TaskList to get tasks for storage
    ui.closeScanner(); // Close the Scanner when done
  }
  
}
