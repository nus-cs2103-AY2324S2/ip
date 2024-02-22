package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.TaskList;
import alpa.utils.Storage;

/**
 * Represents a command that can be executed in the application.
 */
public interface Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList the task list to be operated on
     * @param storage the storage for saving and loading tasks
     * @return the result of executing the command
     * @throws AlpaException if there is an error executing the command
     */
    String executeCommand(TaskList taskList, Storage storage) throws AlpaException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    boolean isExit();
}
