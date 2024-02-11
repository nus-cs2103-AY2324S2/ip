package duke.command;

import duke.Storage;
import duke.TaskList;

public abstract class Command {
    protected boolean isExit = false;
    public boolean isExit() {
        return this.isExit;
    }
    public abstract String execute(TaskList tasks, Storage storage);
}
