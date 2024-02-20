package blu.command;

import java.util.List;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.Task;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to find and display tasks that contain a user specified string.
 */
public class FindCommand extends Command {
    private final String searchString;

    /**
     * Constructs a FindCommand with the specified search string.
     *
     * @param searchString The string to search for within the task descriptions.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes the find command.
     * This method searches the task list for tasks containing the specified search string
     * and displays the found tasks to the user through the UI.
     *
     * @param taskList The TaskList to search for matching tasks.
     * @param storage The Storage (not applicable for FindCommand).
     * @param ui The UI responsible for displaying the found tasks to the user.
     * @return The result of the find command to be displayed to the user.
     * @throws BluException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        List<Task> tasks = taskList.searchForTasksContaining(searchString);
        return ui.showTasksFound(tasks, searchString);
    }
}
