package commands;

import utils.TaskList;
import utils.Ui;
import tasks.Task;

/**
 * The UnmarkCommand class represents a command to unmark a task as completed.
 */
public class UnmarkCommand extends Command {
    private final int i;

    /**
     * Constructs an UnmarkCommand object with the index of the task to be unmarked.
     *
     * @param i The index of the task to be unmarked.
     */
    public UnmarkCommand(int i) {
        this.i = i;
    }

    /**
     * Executes the unmark command.
     *
     * @param ui       The user interface object.
     * @param taskList The task list object.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task updatedTask = taskList.unmarkTask(i - 1); // Adjusting index to match 0-based indexing
        ui.displayUnmarkedTask(updatedTask);
    }
}
