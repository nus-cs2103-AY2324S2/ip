package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

/**
 * @inheritDoc
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Returns a command to mark a task as not done.
     *
     * @param taskNumber Index of the task to mark as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task at the provided index as not done in the list of tasks.
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AdamException {
        String t = tasks.unmark(this.taskNumber);
        return ui.showResult(
                "Nice, I've marked this task as not done:",
                t);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
