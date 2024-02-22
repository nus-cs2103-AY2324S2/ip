package anxi.handlers;

import java.time.LocalDateTime;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.command.TaskList;
import anxi.command.Ui;
import anxi.tasks.Task;

/**
 * Handles inputs related to Deadline tasks.
 */
public class DeadlineHandler extends Handler {

    /**
     * DeadlineHandler constructor.
     */
    public DeadlineHandler() {
    }

    /**
     * Adds new Deadline task.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String       Indicates if task was successfully completed.
     */
    public String addDeadline(String input, Storage storage, TaskList taskList, Ui ui) {
        try {
            return deadline(input, storage, taskList, ui);
        } catch (AnxiException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to add new deadline and update storage.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     * @throws AnxiException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    public String deadline(String input, Storage storage, TaskList taskList, Ui ui) throws AnxiException {
        if (input.isBlank()) {
            throw new AnxiException("Invalid input/syntax. What is due?");
        }

        String[] d = input.split("/by");
        if (d.length < 2) {
            throw new AnxiException("To survive is to procrastinate death, when is this due?");
        }

        LocalDateTime by = parseDateTime(d[1].strip());
        Task task = taskList.addDeadline(d[0].strip(), by);
        addTaskInStorage(storage, task);

        return ui.printAddTask(task.toString(), taskList.getNumOfTasks());
    }
}
