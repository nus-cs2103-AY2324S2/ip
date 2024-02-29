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
     * @param storage The storage to retrieve the tasks to list.
     * @param taskList The task list that stores the tasks to be listed.
     * @return The response containing the list of tasks to be displayed.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return Ui.getListResponse(taskList.list());
    }
}
