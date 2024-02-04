package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import duke.exception.InvalidArgumentException;

public abstract class Command {

    protected boolean isActive = true;

    public boolean getActive() {
        return this.isActive;
    }

    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException;
}
