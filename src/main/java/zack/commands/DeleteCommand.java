package zack.commands;

import zack.ZackException;
import zack.tasks.Task;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;

import java.io.IOException;

/**
 * Command class responsible for deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand, removing the task at the specified index from the task list.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui to display user messages.
     * @param storage The Storage to save the updated task list.
     * @throws ZackException If there is an error deleting the task.
     * @throws IOException   If there is an error in saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new ZackException("Task index is out of range. Please enter a number between 1 and " + tasks.getSize() + ".");
        }
        Task removedTask = tasks.deleteTask(index);
        ui.showDeletedTask(removedTask, tasks.getSize());
        storage.save(tasks.getAllTasks());
    }
}
