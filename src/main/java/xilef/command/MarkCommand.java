package xilef.command;

import xilef.Storage;
import xilef.Ui;
import xilef.Xilef;
import xilef.task.Task;
import xilef.task.TaskCommandPair;
import xilef.task.TaskList;
import xilef.XilefException;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {

    /**
     * The index of the task to mark as done.
     */
    private final int index;

    /**
     * Constructs a new {@code MarkCommand} with the given index.
     *
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Marks the task at the given index as done and saves the updated task list.
     * Displays the task that was marked to the user.
     *
     * @return A string indicating the task is marked and shows task current status.
     * @throws XilefException If the index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws XilefException {
        if (tasks.size() <= index) {
            throw new XilefException("There is nothing to be marked");
        } else {
            Task task = tasks.get(index);
            task.markDone();
            Xilef.push(new TaskCommandPair(task, this));
            storage.save(tasks);
            return ui.showMarked(task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
