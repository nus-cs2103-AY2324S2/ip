package ada.command;

import ada.AdaException;
import ada.Storage;
import ada.task.TaskList;
import ada.ui.Ui;

/**
 * @inheritDoc
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    int taskNumber;

    /**
     * Returns a command that contains the index of the task to delete.
     *
     * @param taskNumber index of the task to delete in the list of tasks
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task at the index provided from the list of tasks.
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdaException {
        String t = tasks.delete(this.taskNumber);
        ui.showResult("Ok, I've removed this task:");
        ui.showResult(t);
        ui.showResult("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
