package anxi.handlers;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.command.TaskList;
import anxi.command.Ui;
import anxi.tasks.Task;

/**
 * Handles inputs related to mark task.
 */
public class MarkHandler extends Handler {

    /**
     * MarkHandler constructor.
     */
    public MarkHandler() {
    }

    /**
     * Marks specific task index as done.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String   Indicates if task was successfully completed.
     */
    public String markTask(String input, Storage storage, TaskList taskList, Ui ui) {
        try {
            return mark(input, storage, taskList, ui);
        } catch (AnxiException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parse and calls relevant methods to mark task as done and update storage.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     * @throws AnxiException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String mark(String input, Storage storage, TaskList taskList, Ui ui) throws AnxiException {
        if (input.isBlank()) {
            throw new AnxiException("Missing index, what to mark?");
        }

        int index = stringToInt(input);
        int numOfTasks = taskList.getNumOfTasks();
        checkOutOfBounds(index, numOfTasks);

        Task t = taskList.markTask(index - 1);
        updateTaskInStorge(storage, t, index, numOfTasks);

        return ui.printMarkTask(t.toString());
    }
}
