package duke;

import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command{

  @Override
  public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException{
      if (userInput.isBlank()) {
          throw new DukeException("The description of a task cannot be empty.");
      }

      Task newTask = Parser.parseTask(userInput);
      if (newTask != null) {
          taskList.addTask(newTask); // Use duke.task.TaskList to add the task
          ui.showTaskAdded(newTask, taskList.getSize());
      } else {
          throw new DukeException("I'm sorry, but I don't know what that means :-(");
      }
  }
  
}
