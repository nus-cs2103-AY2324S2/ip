package anxi.handlers;

import java.io.IOException;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.command.TaskList;
import anxi.command.Ui;
import anxi.tasks.Task;

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
        } catch (AnxiException de) {
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
     * @throws AnxiException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String delete(String input, Storage storage, TaskList taskList, Ui ui) throws AnxiException {
        if (input.matches("")) {
            throw new AnxiException("Missing the target with your input, what to remove?");
        }

        int index = Integer.parseInt(input.strip());
        int numOfTasks = taskList.getNumOfTasks();
        if (((index - 1) < 0) || (index > numOfTasks)) {
            throw new AnxiException("Index out of bounds.");
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
