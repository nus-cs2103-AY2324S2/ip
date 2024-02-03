package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

  public ExitCommand() {}

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showExitMessage();
    ui.showLine();
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
