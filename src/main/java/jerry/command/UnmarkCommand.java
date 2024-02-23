package jerry.command;

import jerry.TaskList;
import jerry.Ui;

/**
 * Represents a command to unmark a specific task, indicating it is not completed.
 * <p>
 * The user specifies the index of the task to unmark. This command updates the task's status
 * and notifies the user that the task is no longer marked as done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code UnmarkCommand} for marking a task as not completed.
     *
     * @param ui        The UI component for interacting with the user.
     * @param tasks     The task list containing the task to be unmarked.
     * @param taskIndex The index of the task to be unmarked as done, as displayed to the user.
     */
    public UnmarkCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the process of unmarking the specified task.
     * <p>
     * If the specified index is invalid, an error message is displayed to the user.
     */
    @Override
    public String execute() {
        try {
            tasks.unmark(taskIndex);
            return ui.showUnmark(tasks, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            return ui.showMessage("Task index is out of bounds.");
        } catch (Exception e) {
            return ui.showMessage("Task index must be a number");
        }
    }
}
