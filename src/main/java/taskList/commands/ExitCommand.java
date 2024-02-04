package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

public class ExitCommand implements Command {

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) {
    ui.setRunning(false);
    ui.showEndMessage();
  }
}
