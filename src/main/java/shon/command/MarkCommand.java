package shon.command;

import shon.TaskList;
import shon.exception.ParameterException;

/**
 * Represents a command to mark a specific task in the <code>TaskList</code> as done.
 */
public class MarkCommand extends Command {
    private int idx;

    /**
     * Creates a new mark command to mark the task indicated by idx as done.
     *
     * @param idx The index of the task to be marked as done.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks the task indicated by idx in the <code>TaskList</code> as done, and
     * outputs the result of the command.
     *
     * @param tasks The <code>TaskList</code> containing the task to be marked as done.
     * @throws ParameterException If the given index is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws ParameterException {
        return String.join("\n", tasks.mark(this.idx));
    }
}
