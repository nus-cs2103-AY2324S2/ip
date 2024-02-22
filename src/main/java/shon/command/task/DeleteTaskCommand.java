package shon.command.task;

import shon.exception.ParameterException;
import shon.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends TaskCommand {
    private int idx;

    /**
     * Creates a new command to delete a task indicated by idx.
     *
     * @param tasks The TaskList associated with this command.
     * @param idx The index of the task to be deleted.
     */
    public DeleteTaskCommand(TaskList tasks, int idx) {
        super(tasks);
        this.idx = idx;
    }

    /**
     * Deletes the task from the tasks <code>TaskList</code>, and outputs the result of the command.
     *
     * @throws ParameterException If the given index is invalid.
     */
    @Override
    public String execute() throws ParameterException {
        return String.join("\n", this.tasks.deleteTask(this.idx));
    }
}
