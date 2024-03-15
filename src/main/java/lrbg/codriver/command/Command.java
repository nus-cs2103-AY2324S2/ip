package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws CoDriverException If an error occurs during execution.
     * @return The response to the user.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}