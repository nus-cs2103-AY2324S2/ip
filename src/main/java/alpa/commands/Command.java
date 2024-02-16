package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.utils.Storage;

/**
 * Represents a command that can be executed in the application.
 */
public interface Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList the task list to be operated on
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving and loading tasks
     * @throws AlpaException if there is an error executing the command
     */
    void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    boolean isExit();
}
