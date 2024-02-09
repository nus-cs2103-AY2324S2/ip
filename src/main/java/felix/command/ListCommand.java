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
     * Returns String representation of all tasks within task list.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        return ui.toString(tasks);
    }
}
