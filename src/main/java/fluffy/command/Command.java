package fluffy.command;

import fluffy.FluffyException;
import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     * @throws FluffyException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FluffyException;

    /**
     * Returns whether the command is an exit command.
     * @return Whether the command is an exit command.
     */
    public abstract boolean isExit();
}

