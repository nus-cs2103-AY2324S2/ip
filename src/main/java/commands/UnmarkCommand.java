package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This class represents a command for marking a task as not completed in the task list
 * managed by the chatbot. When executed, this command updates the status of the
 * specified task to indicate that it has not been completed.
 */
public class UnmarkCommand extends Command {
    private static final String COMMAND = "unmark ";
    /**
     * Executes the command to mark a task in the list of recorded tasks as not done.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.UNMARK);
        int i = getTaskListNumber(message.split(" ")[1]);
        assert message.length() >= (COMMAND + "#").length() : "Input not handled properly";
        return tasks.unmarkTask(i);
    }
}
