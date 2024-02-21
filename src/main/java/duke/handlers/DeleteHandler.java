package duke.handlers;

import java.io.IOException;

import duke.command.DukeException;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.tasks.Task;

/**
 * Handles inputs related to delete tasks.
 */
public class DeleteHandler {

    /**
     * EventHandler constructor.
     */
    public DeleteHandler() {
    }

    /**
     * Deletes task from list.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     */
    public String deleteTask(String input, Storage storage, TaskList taskList, Ui ui) {
        try {
            return delete(input, storage, taskList, ui);
        } catch (DukeException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to add new todo and update storage.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     * @throws DukeException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String delete(String input, Storage storage, TaskList taskList, Ui ui) throws DukeException {
        if (input.matches("")) {
            throw new DukeException("Missing the target with your input, what to remove?");
        }

        int index = Integer.parseInt(input.strip());
        int numOfTasks = taskList.getNumOfTasks();
        if (((index - 1) < 0) || (index > numOfTasks)) {
            throw new DukeException("Index out of bounds.");
        }

        Task task = taskList.deleteTask(index - 1);
        try {
            storage.deleteTask(index - 1, numOfTasks);
        } catch (IOException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        return ui.printDeleteTask(task.toString(), taskList.getNumOfTasks());
    }
}
