package Command;

import Task.TaskList;
import Dook.Ui;
import Dook.Storage;
import Dook.DookException;

public abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DookException;
}