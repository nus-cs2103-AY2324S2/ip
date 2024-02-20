package duke.command;

import java.io.IOException;

import duke.tasks.Task;

/**
 * Handles inputs related to Deadline tasks.
 */
public class DeadlineHandler {

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
        } catch (DukeException de) {
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
     * @throws DukeException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    public String deadline(String input, Storage storage, TaskList taskList, Ui ui) throws DukeException {
        if (input.matches("")) {
            throw new DukeException("Invalid input/syntax. What is due?");
        }

        String[] d = input.split("/by");
        if (d.length < 2) {
            throw new DukeException("To survive is to procrastinate death, when is this due?");
        }

        Task task = taskList.addDeadline(d[0].strip(), d[1].strip());
        try {
            storage.addNewTask(task);
        } catch (IOException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        return ui.printAddTask(task.toString(), taskList.getNumOfTasks());
    }
}
