package commands;

import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This class represents a command for listing all tasks in the task list managed
 * by the chatbot. When executed, this command retrieves and displays all tasks
 * currently stored in the task list, along with their details.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list out the tasks in the list of recorded tasks.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        return tasks.toString();
    }
}
