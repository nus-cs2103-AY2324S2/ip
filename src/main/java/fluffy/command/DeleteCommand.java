package fluffy.command;

import fluffy.FluffyException;
import fluffy.storage.Storage;
import fluffy.task.Task;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Constructor for DeleteCommand.
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     * @throws FluffyException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FluffyException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showTaskDeleted(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
