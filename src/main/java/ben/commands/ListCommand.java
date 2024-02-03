package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {

  public ListCommand() {}

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showListMessage();
    ui.showTaskList(tasks);
  }
}
