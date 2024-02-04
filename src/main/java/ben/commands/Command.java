package ben.commands;

import ben.exceptions.BenException;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

public abstract class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        throw new UnsupportedOperationException("This method is meant to be used by a child class.");
    }

    public boolean isExit() {
        return false;
    }
}
