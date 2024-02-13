package commands;

import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This abstract class serves as the base for implementing various commands
 * that the chatbot can understand and execute. Each concrete command should
 * extend this class and implement the execute() method to define its specific
 * functionality.
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
}
