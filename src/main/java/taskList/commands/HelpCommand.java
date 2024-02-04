package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

public class HelpCommand implements Command {

  @Override
  public void execute(TaskList taskList, Ui ui, Storage storage) {
    ui.showHelpMessage();
  }
}
