package duke.handlers;

import java.io.IOException;

import duke.command.DukeException;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.tasks.Task;

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
        } catch (DukeException de) {
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
     * @throws DukeException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String unmark(String input, Storage storage, TaskList taskList, Ui ui) throws DukeException {
        if (input.matches("")) {
            throw new DukeException("Missing index, what to unmark?");
        }

        int index = Integer.parseInt(input.strip());
        int numOfTasks = taskList.getNumOfTasks();
        if (((index - 1) < 0) || (index > numOfTasks)) {
            throw new DukeException("Index out of bounds, no task found.");
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
