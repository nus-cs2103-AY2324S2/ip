package james.commands;

import james.exception.DukeException;
import james.storage.Storage;
import james.tasklist.TaskList;
import james.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
