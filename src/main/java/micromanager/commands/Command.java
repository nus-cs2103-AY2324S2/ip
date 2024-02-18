package micromanager.commands;

import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;

/**
 * Command is an abstract class representing a command to be executed by the application.
 * It provides an execute method that subclasses must implement to carry out the specific command.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list and storage.
     *
     * @param taskList The list of tasks.
     * @param storage  The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
