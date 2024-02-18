package command;

import exceptions.DukeException;
import task.TaskList;
import utilities.Storage;

/**
 * Controls what to do when user inputs something.
 */
public abstract class Command {
    /**
     * Variable to tell the program whether to exit or not.
     */
    private boolean isExit;

    /**
     * Executes the task process.
     * @param taskList The task list that the command is applied to.
     * @param storage The storage that the task list is stored in after the command is applied.
     * @return The response expected from the chatbot.
     * @throws DukeException The exception thrown when the process is unsuccessful.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;
}
