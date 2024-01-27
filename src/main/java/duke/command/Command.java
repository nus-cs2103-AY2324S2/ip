package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    protected final void setExit() {
        isExit = true;
    }

    public final boolean isExit() {
        return isExit;
    }
}
