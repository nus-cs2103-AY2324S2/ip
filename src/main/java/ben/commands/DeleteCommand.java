package ben.commands;

import ben.exceptions.BenException;
import ben.storage.Storage;
import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand with the specified index to delete a task.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by removing the task at the specified index from the task list.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws BenException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        if (tasks.isEmpty()) {
            throw new BenException("   No tasks to delete :)");
        }

        if (tasks.isOutOfBounds(this.index)) {
            throw new BenException("   Please input a valid number between 1 and " + tasks.size());
        }

        Task deletedTask = tasks.removeTask(index);

        return ui.showDeletedTaskMessage() +
                ui.showTask(deletedTask);
    }
}
