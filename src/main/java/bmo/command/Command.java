package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

public abstract class Command {

    private final boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }

    ;
}
