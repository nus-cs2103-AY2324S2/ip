package raphael.command;

import raphael.storage.Storage;
import raphael.task.Task;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * Adds the task upon execution.
 */
public class AddCommand extends Command {
    private final Task toAdd;
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the current command. Upon execution, the task stored as instance field will be added to the task list.
     * The Ui object will print the relevant output onto the terminal. The file used to store the tasks
     * will be updated by the Storage object.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the file I/O object
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws raphael.exception.RaphaelException {
        tasks.addTask(this.toAdd);
        ui.showAddOutput(tasks, this.toAdd);
        storage.write(tasks.toFileFormat());
    }
}
