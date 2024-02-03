package commands;

import exceptions.BenException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
  private final int index;

  public UnmarkCommand(int index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {

    // check if task list is empty
    if (tasks.isEmpty()) {
      throw new BenException("   There are no pending tasks now... Add some tasks here!");
    }

    // check if input is within bounds
    if (tasks.isWithinBounds(index)) {
      throw new BenException("   Please input a valid number between 1 and " + tasks.size());
    }

    tasks.unmarkTask(index);

    ui.showUnmarkedTaskMessage();
    ui.showTask(tasks, index);
  }
}
