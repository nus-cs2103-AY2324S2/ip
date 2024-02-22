package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Abstract class which serves as the base for implementing various commands
 * that the chatbot can understand and execute.
 */
public abstract class Command {

    /**
     * Executes command based on the user input.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException;

    /**
     * Checks whether the string is a valid number and returns the number as an int if the string is valid.
     *
     * @param number Input String which represents TaskList number
     * @return TaskList number as an int
     * @throws InvalidFormatException if the number is not valid
     */
    public int getTaskListNumber(String number, LeluException.ErrorType err) throws InvalidFormatException {
        int i = 0;
        try {
            i = Integer.parseInt(number) - 1;
        } catch (NumberFormatException e) {
            InvalidFormatException.callInvalidFormatException(err);
        }
        return i;
    }

    public void checkEmptyDescription(String message, String command, LeluException.ErrorType error) throws InvalidFormatException {
        if (message.trim().equals(command.trim())) {
            InvalidFormatException.callInvalidFormatException(error);
        }
    }
}
