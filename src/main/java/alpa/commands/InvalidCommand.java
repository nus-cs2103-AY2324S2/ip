package alpa.commands;

import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.utils.Storage;

/**
 * Represents an invalid command.
 * This class is used to create an instance of an invalid command with a specific error message.
 */
public class InvalidCommand implements Command {
    private String message;

    /**
     * Constructs a new InvalidCommand object with the given error message.
     * @param message The error message associated with the invalid command.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the invalid command by displaying an error message on the user interface.
     *
     * @param taskList the task list containing the tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving and loading tasks
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showError(message);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
