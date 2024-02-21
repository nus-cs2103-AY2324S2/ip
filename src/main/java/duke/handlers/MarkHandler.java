package duke.handlers;

import java.io.IOException;

import duke.command.DukeException;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.tasks.Task;

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
        } catch (DukeException de) {
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
     * @throws DukeException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String mark(String input, Storage storage, TaskList taskList, Ui ui) throws DukeException {
        if (input.matches("")) {
            throw new DukeException("Missing index, what to mark?");
        }

        int index = Integer.parseInt(input.strip());
        int numOfTasks = taskList.getNumOfTasks();
        if (((index - 1) < 0) || (index > numOfTasks)) {
            throw new DukeException("Index out of bounds, no task found.");
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
