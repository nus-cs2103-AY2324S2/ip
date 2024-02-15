package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.utils.Storage;
import alpa.tasks.Task;
import alpa.tasks.TaskList;
import alpa.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
  private final int index;
  
  /**
   * Constructs a DeleteCommand object with the specified index.
   * 
   * @param indexStr the index of the task to be deleted as a string
   * @throws AlpaException if the index is not a valid integer
   */
  public DeleteCommand(String indexStr) throws AlpaException {
    try {
      this.index = Integer.parseInt(indexStr) - 1;
    } catch (NumberFormatException e) {
      throw new AlpaException("\nInvalid input for delete, human!!");
    }
  }

  /**
   * Executes the delete command, which removes a task from the task list.
   * Throws an AlpaException if the index is invalid.
   *
   * @param taskList the task list containing the tasks
   * @param ui the user interface for displaying messages
   * @param storage the storage for saving tasks
   * @throws AlpaException if the index is out of bounds
   */
  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
    if (index < 0 || index >= taskList.getSize()) {
      throw new AlpaException("Invalid task number, human!!");
    }
    Task removedTask = taskList.deleteTask(index);
    ui.showDeletedTask(removedTask, taskList.getSize());
    storage.saveTasks(taskList.getTasks());
  }

  /**
   * Returns whether or not the command should exit the program.
   *
   * @return true if the command should exit the program, false otherwise.
   */
  @Override
  public boolean isExit() {
    return false;
  }
}
