package xilef.command;

import xilef.Storage;
import xilef.Ui;
import xilef.Xilef;
import xilef.task.Task;
import xilef.task.TaskCommandPair;
import xilef.task.TaskList;
import xilef.XilefException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to delete.
     */
    private final int index;

    /**
     * Constructs a new {@code DeleteCommand} with the given index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes the task at the given index from the task list and saves the updated task list.
     * Displays the task that was deleted to the user.
     *
     * @return A string indicating the task that was deleted and the updated task list size.
     * @throws XilefException If the index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws XilefException {
        if (tasks.size() <= index) {
            throw new XilefException("There is nothing to be deleted");
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            Xilef.push(new TaskCommandPair(task, this));
            storage.save(tasks);
            return ui.showDeleted(task) + "\n" + ui.showSize(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
