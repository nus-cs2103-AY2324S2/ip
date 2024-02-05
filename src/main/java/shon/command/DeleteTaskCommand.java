package shon.command;

import shon.exception.ParameterException;

import shon.TaskList;
import shon.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private int idx;

    /**
     * Creates a new command to delete a task indicated by idx.
     * @param idx The index of the task to be deleted.
     */
    public DeleteTaskCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Deletes the task from the tasks <code>TaskList</code>, and outputs the result of the command.
     *
     * @param tasks The <code>TaskList</code> to delete the <code>Task</code> from.
     * @param ui The <code>Ui</code> used to output the result of the command.
     * @throws ParameterException If the given index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws ParameterException {
        ui.print(tasks.deleteTask(this.idx));
    }
}
