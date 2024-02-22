package jerry.command;

import jerry.Task;
import jerry.TaskList;
import jerry.Ui;

/**
 * Represents a command to delete a specific task from the task list.
 * <p>
 * This command allows users to remove tasks by specifying the task's index in the task list.
 * The index is expected to be one-based, as presented to the user.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the necessary UI, task list, and the index of the task to be deleted.
     *
     * @param ui        The UI component for interacting with the user.
     * @param tasks     The task list from which a task will be deleted.
     * @param taskIndex The one-based index of the task to delete.
     */
    public DeleteCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the task deletion process.
     * <p>
     * The task specified by the index is removed from the task list. If the index is out of bounds,
     * an appropriate error message is displayed to the user.
     */
    @Override
    public String execute() {
        try {
            Task removedTask = tasks.deleteTask(taskIndex);
            return ui.showDelete(tasks, removedTask);
        } catch (IndexOutOfBoundsException e) {
            return ui.showMessage("Task index is out of bounds.");
        } catch (Exception e) {
            return ui.showMessage("Task index must be a number");
        }
    }
}
