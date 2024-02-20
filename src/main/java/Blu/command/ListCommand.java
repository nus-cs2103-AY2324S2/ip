package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * This method retrieves all the tasks from the task list and displays them through the UI.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     * @param storage The Storage (not applicable for ListCommand).
     * @param ui The UI responsible for displaying the task list to the user.
     * @return The string representation of the tasks in task list.
     * @throws BluException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        return ui.showTaskList(taskList);
    }
}
