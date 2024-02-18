package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        String t = taskList.delete(taskNumber);
        return ui.showResult(
                "Ok, I've removed this task:",
                t,
                "Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
