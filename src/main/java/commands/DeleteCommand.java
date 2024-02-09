package commands;

import helpers.TaskList;
import helpers.Ui;
import tasks.Task;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int i;

    /**
     * Constructs a DeleteCommand object with the index of the task to be deleted.
     *
     * @param i The index of the task to be deleted.
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Executes the delete command.
     *
     * @param ui       The user interface object.
     * @param taskList The task list object.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task deletedTask = taskList.deleteTask(i - 1); // Adjusting index to match 0-based indexing
        ui.displayDeletedTask(deletedTask, taskList.getLength());
    }
}
