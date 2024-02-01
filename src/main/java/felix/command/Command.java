package felix.command;

import felix.exception.FelixException;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

public abstract class Command {
    protected boolean isExit = false;
    public boolean isExit() {
        return this.isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException;
}