package saopig.command;

import saopig.Storage;
import saopig.Ui;
import saopig.task.TaskList;

/**
 * Abstract class for all commands.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    public abstract boolean isExit();
}
