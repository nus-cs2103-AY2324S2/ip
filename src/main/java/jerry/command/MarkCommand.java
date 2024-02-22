package jerry.command;

import jerry.TaskList;
import jerry.Ui;

/**
 * Represents a command to mark a specific task as completed.
 * <p>
 * The user specifies the index of the task to mark as done. This command updates the task's status
 * and notifies the user of the successful operation.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} for marking a task as completed.
     *
     * @param ui        The UI component for interacting with the user.
     * @param tasks     The task list containing the task to be marked.
     * @param taskIndex The index of the task to be marked as done, as displayed to the user.
     */
    public MarkCommand(Ui ui, TaskList tasks, int taskIndex) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the process of marking the specified task as completed.
     * <p>
     * If the specified index is invalid, an error message is displayed to the user.
     */
    @Override
    public String execute() {
        try {
            tasks.mark(taskIndex);
            return ui.showMark(tasks, taskIndex);
        } catch (IndexOutOfBoundsException e) {
            return ui.showMessage("Task index is out of bounds.");
        } catch (Exception e) {
            return ui.showMessage("Task index must be a number");
        }
    }
}
