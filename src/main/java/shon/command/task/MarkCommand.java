package shon.command.task;

import shon.exception.ParameterException;
import shon.task.TaskList;

/**
 * Represents a command to mark a specific task in the <code>TaskList</code> as done.
 */
public class MarkCommand extends TaskCommand {
    private int idx;

    /**
     * Creates a new mark command to mark the task indicated by idx as done.
     *
     * @param tasks The TaskList associated with this command.
     * @param idx The index of the task to be marked as done.
     */
    public MarkCommand(TaskList tasks, int idx) {
        super(tasks);
        this.idx = idx;
    }

    /**
     * Marks the task indicated by idx in the <code>TaskList</code> as done, and
     * outputs the result of the command.
     *
     * @throws ParameterException If the given index is invalid.
     */
    @Override
    public String execute() throws ParameterException {
        return String.join("\n", this.tasks.mark(this.idx));
    }
}
