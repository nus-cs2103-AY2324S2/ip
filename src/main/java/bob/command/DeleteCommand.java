package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.InvalidTaskIndexException;
import bob.exception.SavingException;
import bob.task.Task;

/**
 * Represents an action to delete a task. A <code>DeleteCommand</code> object corresponds to
 * a command to delete a task with a task index.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Returns a command to delete a task with a specified task index.
     *
     * @param taskIndex The task index for the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete a task with a specified task index.
     *
     * @param ui The UI to display the result of deleting the task.
     * @param storage The storage to update the task list in hard disk.
     * @param taskList The task list to store the list of tasks after the deletion.
     * @throws InvalidTaskIndexException If the specified task index is invalid.
     * @throws SavingException If there was an error updating the task list in hard disk.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws InvalidTaskIndexException, SavingException {
        Task task = taskList.delete(taskIndex);
        ui.showDelete(task, taskList.getSize());
        taskList.updateStorage(storage);
    }
}
