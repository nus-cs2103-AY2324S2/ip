package felix.command;

import felix.exception.FelixException;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing command to list all tasks
 */
public class ListCommand extends Command {
    /**
     * Prints out all tasks within task list to console.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.println(tasks);
    }
}
