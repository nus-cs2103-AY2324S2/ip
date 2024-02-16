package raphael.command;

import raphael.storage.Storage;
import raphael.task.TaskList;
import raphael.ui.Ui;

/**
 * Lists all the tasks in the task list
 */
public class ListCommand extends Command {
    /**
     * Executes the current command. Upon execution, all the tasks stored in the task list are printed.
     * The Ui object will print the relevant output onto the terminal.
     * @param tasks the task list.
     * @param ui the user interface object.
     * @param storage the file I/O object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListOutput(tasks.listTasks());
    }
}
