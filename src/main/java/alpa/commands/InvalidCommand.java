package alpa.commands;

import alpa.utils.Storage;
import alpa.tasks.TaskList;
import alpa.ui.Ui;

public class InvalidCommand implements Command {
  private String message;

  public InvalidCommand(String message) {
    this.message = message;
  }

  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
    ui.showError(message);
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
