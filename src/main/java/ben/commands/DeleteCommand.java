package commands;

import exceptions.BenException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
  private final int index;

  public DeleteCommand(int index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
    if (tasks.isEmpty()) {
      throw new BenException("   No tasks to delete :)");
    }

    if (tasks.isWithinBounds(this.index)) {
      throw new BenException("   Please input a valid number between 1 and " + tasks.size());
    }

    // delete task from list
    Task deletedTask = tasks.removeTask(index);

    ui.showDeletedTaskMessage();
    ui.showTask(deletedTask);

  }
}
