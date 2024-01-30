package command;

import utilities.Storage;
import task.TaskList;
import utilities.Ui;

import exceptions.DukeException;

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
