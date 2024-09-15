package raphael.command;

import raphael.exception.RaphaelException;
import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * Deletes a task upon execution.
 */
public class DeleteCommand extends Command {
    private final int idx;
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the current command. Upon execution, the task indicated by the instance field will be deleted
     * from the task list. The Ui object will print the relevant output onto the terminal.
     * The file used to store the tasks will be updated by the Storage object.
     *
     * @param tasks the task list
     * @param ui the user interface object
     * @param storage the file I/O object
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException {
        ui.showDeleteOutput(tasks.deleteTask(idx));
        storage.write(tasks.toFileFormat());
    }
}
