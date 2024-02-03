package commands;

import exceptions.BenException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
  public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
    throw new UnsupportedOperationException("This method is meant to be used by a child class.");
  }

  public boolean isExit() {
    return false;
  }
}
