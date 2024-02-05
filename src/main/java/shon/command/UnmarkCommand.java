package shon.command;

import shon.exception.ParameterException;

import shon.TaskList;
import shon.Ui;

/**
 * Represents a command to mark a specific task in the <code>TaskList</code> as not done.
 */
public class UnmarkCommand extends Command {
    private int idx;

    /**
     * Creates a new mark command to mark the task indicated by idx as not done.
     *
     * @param idx The index of the task to be marked as not done.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Marks the task indicated by idx in the <code>TaskList</code> as not done, and
     * outputs the result of the command.
     *
     * @param tasks The <code>TaskList</code> containing the task to be marked as not done.
     * @param ui The <code>Ui</code> used to output the result of the command.
     * @throws ParameterException If the given index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws ParameterException {
        ui.print(tasks.unmark(this.idx));
    }
}
