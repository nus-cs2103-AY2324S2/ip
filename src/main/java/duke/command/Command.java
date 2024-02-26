package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Class representing an abstract command.
 */
public abstract class Command {
    protected boolean isExit = false;
    public boolean isExit() {
        return this.isExit;
    }
    public abstract String execute(TaskList tasks, Storage storage);
}
