/* (C)2024 */
package taskList.commands;

import taskList.Storage;
import taskList.TaskList;
import taskList.Ui;

public class InvalidCommand implements Command {
  private String errorMessage;

  public InvalidCommand(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) {
    // Implementation for handling the execution of an invalid command
    // This can include displaying an error message to the user
    ui.showErrorMessage(errorMessage);
  }
}
