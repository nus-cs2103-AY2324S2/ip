package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Represents an action to list the tasks. A <code>ListCommand</code> object corresponds to
 * a command to list all tasks currently in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list the tasks.
     *
     * @param ui The UI to display the list.
     * @param storage The storage to retrieve the tasks to list.
     * @param taskList The task list that stores the tasks to be listed.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showList(taskList.list());
    }
}
