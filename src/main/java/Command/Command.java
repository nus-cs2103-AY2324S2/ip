package Command;

import Utilities.Storage;
import Task.TaskList;
import Utilities.Ui;

import Exceptions.DukeException;

public abstract class Command {
    private boolean isExit;
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;
}
