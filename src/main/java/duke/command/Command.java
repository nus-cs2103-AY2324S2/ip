package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public void exit() {
        this.isExit = !this.isExit;
    }

    public boolean isExit() {
        return false; // Default implementation, can be overridden by subclasses
    }
}

