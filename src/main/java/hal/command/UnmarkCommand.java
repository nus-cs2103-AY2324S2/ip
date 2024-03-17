package hal.command;

import hal.task.TaskList;

/**
 * The UnmarkCommand class represents a command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new UnmarkCommand object.
     *
     * @param taskIndex The index of the task to be marked as undone.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message indicating the result of the unmark operation.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.markAsUndone(taskIndex);
    }
}
