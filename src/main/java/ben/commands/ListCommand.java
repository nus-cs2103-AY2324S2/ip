package ben.commands;

import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

public class ListCommand extends Command {

  public ListCommand() {}

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showListMessage();
    ui.showTaskList(tasks);
  }
}
