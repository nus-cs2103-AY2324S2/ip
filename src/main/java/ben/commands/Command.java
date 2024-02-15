package ben.commands;

import ben.exceptions.BenException;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

/**
 * Represents an abstract command that can be executed on the task list.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to perform the command on.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save the task list changes.
     * @throws BenException If an error occurs during the execution of the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        throw new UnsupportedOperationException("This method is meant to be used by a child class.");
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */

    public boolean isExit() {
        return false;
    }
}
