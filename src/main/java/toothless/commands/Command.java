package toothless.commands;

import toothless.Ui;
import toothless.TaskList;
import toothless.Storage;
import toothless.ToothlessException;

/**
 * This abstract class represents a command within the Toothless application.
 * Commands are used to interact with the application's user interface, task list, and storage system,
 * potentially throwing a ToothlessException to signal issues specific to command execution.
 */
public abstract class Command {

    /**
     * Executes the command with the specified UI, task list, and storage.
     * This method is to be implemented by subclasses to define specific command behavior.
     * @param ui The user interface to interact with.
     * @param taskList The task list to be manipulated or queried.
     * @param storage The storage system for loading or saving tasks.
     * @return true if the application should continue running, false otherwise.
     * @throws ToothlessException If an error specific to the Toothless application occurs during command execution.
     */
    public abstract boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException;

    /**
     * Converts a string detail into an integer index for task manipulation.
     * The method adjusts the index to accommodate zero-based indexing used internally.
     * @param detail The string detail, typically representing the task number entered by the user.
     * @return The integer index corresponding to the user's input, adjusted for zero-based indexing.
     * @throws ToothlessException If the provided detail is not a valid integer.
     */
    public int getTaskIndex(String detail) throws ToothlessException{
        try {
            int taskIndex = Integer.valueOf(detail);
            return taskIndex - 1;
        } catch (NumberFormatException e){
            throw new toothless.ToothlessException("Number put is not number.\nPlease put real number ._.");
        }
    }
}
