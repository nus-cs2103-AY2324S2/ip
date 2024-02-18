package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This class represents a command for deleting a task from the task list managed
 * by the chatbot. When executed, this command removes the specified task from the
 * task list based on its index.
 */
public class DeleteCommand extends Command {
    private static final String COMMAND = " delete ";
    /**
     * Executes the command to remove a task from the list of recorded tasks.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.DELETE);
        int i = getTaskListNumber(message.split(" ")[1]);
        assert message.length() >= (COMMAND + "#").length() : "Input not handled properly";
        return tasks.removeTask(i);
    }
}
