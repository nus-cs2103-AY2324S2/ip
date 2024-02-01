package command;

import exception.BuddyException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents Command that deletes task from task list.
 */
public class DeleteCommand extends Command {
    protected int taskIndex;

    /**
     * Creates DeleteCommand with specified task to be deleted.
     *
     * @param taskIndex Index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task from TaskList.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param ui Current Ui object used.
     * @param storage Current Storage object used.
     * @throws BuddyException If given index of task is not in TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        try {
            Task task = tasks.getTask(taskIndex);
            tasks.deleteTask(task);
            ui.showDelete(task, tasks.size());
        } catch (IndexOutOfBoundsException ioobe) {
            throw new BuddyException("Not a valid task number buddy!");
        }
    }


}
