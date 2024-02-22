package alpa.commands;

import alpa.tasks.TaskList;
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
     * Executes the invalid command and returns the error message.
     *
     * @param taskList the task list to operate on
     * @param storage the storage to save the task list
     * @return the error message
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return message; // Return the error message directly
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
