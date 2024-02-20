package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

public abstract class Command {
    protected final String command;

    public Command(String command) {
        this.command = command;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }

    public String toString() {
        return "Command";
    }
}
