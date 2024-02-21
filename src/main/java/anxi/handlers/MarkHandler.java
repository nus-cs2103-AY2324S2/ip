package anxi.handlers;

import java.io.IOException;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.command.TaskList;
import anxi.command.Ui;
import anxi.tasks.Task;

/**
 * Handles inputs related to mark task.
 */
public class MarkHandler {

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
        if (input.matches("")) {
            throw new AnxiException("Missing index, what to mark?");
        }

        int index = Integer.parseInt(input.strip());
        int numOfTasks = taskList.getNumOfTasks();
        if (((index - 1) < 0) || (index > numOfTasks)) {
            throw new AnxiException("Index out of bounds, no task found.");
        }

        Task t = taskList.markTask(index - 1);
        try {
            storage.updateTask(t, index, numOfTasks);
        } catch (IOException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        return ui.printMarkTask(t.toString());
    }
}
