package commands;

import helpers.TaskList;
import helpers.Ui;
import tasks.Task;

/**
 * The MarkCommand class represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final int i;

    /**
     * Constructs a MarkCommand object with the index of the task to be marked as completed.
     *
     * @param i The index of the task to be marked as completed.
     */
    public MarkCommand(int i) {
        this.i = i;
    }

    /**
     * Executes the mark command.
     *
     * @param ui       The user interface object.
     * @param taskList The task list object.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task updatedTask = taskList.markTask(i - 1); // Adjusting index to match 0-based indexing
        ui.displayMarkedTask(updatedTask); // Display the updated task to the user
    }
}
