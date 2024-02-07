package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command in the application. This abstract class serves as a base
 * for specific commands within the application.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, storage, and user interface.
     * Each concrete command class provides its implementation of this method.
     *
     * @param taskList The list of tasks on which the command operates.
     * @param storage The storage system used for persisting tasks.
     * @param ui The user interface used for interaction with the user.
     * @return The message to be displayed to the user.
     * @throws BluException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList taskList, Storage storage, UI ui) throws BluException;
}
