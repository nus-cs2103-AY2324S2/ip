package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.InvalidTaskIndexException;
import bob.exception.SavingException;
import bob.task.Task;

/**
 * Represents an action to mark or unmark a task with a specified task index.
 * A <code>MarkCommand</code> object corresponds to a command to mark a task as done or undone.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isDone;

    /**
     * Returns a command to mark or unmark a task with a specified task index.
     *
     * @param taskIndex The task index for the task to be marked or unmarked.
     * @param isDone Whether the task is to be marked or unmarked.
     */
    public MarkCommand(int taskIndex, boolean isDone) {
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    /**
     * Executes the command to mark or unmark a task with a specified task index.
     *
     * @param ui The UI to display the result of marking or unmarking the task.
     * @param storage The storage to update the task list in hard disk.
     * @param taskList The task list to store the list of tasks after the mark or unmark.
     * @throws InvalidTaskIndexException If the specified task index is invalid.
     * @throws SavingException If there was an error updating the task list in hard disk.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws InvalidTaskIndexException, SavingException {
        Task task = taskList.mark(taskIndex, isDone);
        ui.showMark(task, task.getDone());
        taskList.updateStorage(storage);
    }
}
