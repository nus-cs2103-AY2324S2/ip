package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.utils.Storage;
import alpa.tasks.TaskList;
import alpa.tasks.Task;
import alpa.ui.Ui;

public class MarkCommand implements Command {
  private int index;

  public MarkCommand(String index) {
    this.index = Integer.parseInt(index.trim()) - 1;
  }

  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
    Task task = taskList.markTaskAsDone(index);
    ui.showMarkedAsDone(task);
    storage.saveTasks(taskList.getTasks());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
