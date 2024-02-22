package anxi.handlers;

import java.io.IOException;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.command.TaskList;
import anxi.command.Ui;
import anxi.tasks.Task;

/**
 * Handles inputs related to unmark task.
 */
public class UnmarkHandler {

    /**
     * UnmarkHandler constructor.
     */
    public UnmarkHandler() {
    }

    /**
     * Unmarks specific task index.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String   Indicates if task was successfully completed.
     */
    public String unmarkTask(String input, Storage storage, TaskList taskList, Ui ui) {
        try {
            return unmark(input, storage, taskList, ui);
        } catch (AnxiException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to unmark task and update storage.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     * @throws AnxiException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String unmark(String input, Storage storage, TaskList taskList, Ui ui) throws AnxiException {
        if (input.isBlank()) {
            throw new AnxiException("Missing index, what to unmark?");
        }

        int index;
        try {
            index = Integer.parseInt(input.strip());
        } catch (NumberFormatException n) {
            throw new AnxiException("Tsk tsk integers only.");
        }

        int numOfTasks = taskList.getNumOfTasks();
        if (((index - 1) < 0) || (index > numOfTasks)) {
            throw new AnxiException("Index out of bounds, no task found.");
        }

        Task t = taskList.unmarkTask(index - 1);
        try {
            storage.updateTask(t, index, numOfTasks);
        } catch (IOException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        return ui.printUnmarkTask(t.toString());
    }
}
