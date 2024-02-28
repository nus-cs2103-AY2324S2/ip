package bmo.command;

import bmo.ui.Ui;
import bmo.util.Storage;
import bmo.util.TaskList;

/**
 * Sorts the TaskList when executed.
 */
public class SortCommand extends Command {
    public SortCommand() {
        super();
    }

    /**
     * Sorts the TaskList and returns a ui message
     *
     * @param tasks   TaskList object to store the list of tasks.
     * @param ui      Ui object to handle user interface.
     * @param storage Storage object to handle file storage.
     * @return String containing the result of the command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.sortTaskList();
    }
}
