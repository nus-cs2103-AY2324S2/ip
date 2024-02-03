package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

import duke.exception.InvalidArgumentException;

public abstract class Command {
    boolean isActive = true;
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException;

    public boolean getIsActive() {
        return this.isActive;
    }
}
