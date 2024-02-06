package ada.command;

import ada.AdaException;
import ada.Storage;
import ada.task.TaskList;
import ada.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdaException {
        String t = tasks.unmark(this.taskNumber);
        ui.showResult("Nice, I've marked this ada.task as done:");
        ui.showResult(t);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
