package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to search for tasks by a specified keyword.
 * This command allows users to find tasks that contain the given keyword in their description.
 */
public class FindTaskCommand extends Command {
    private final String keyword; // Declare as final since it should not change once initialized

    /**
     * Creates a FindTaskCommand with the specified keyword for searching tasks.
     *
     * @param keyword The keyword used to find tasks containing matching text.
     */
    public FindTaskCommand(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword for finding tasks cannot be null or empty.");
        }
        this.keyword = keyword.trim(); // Trim the keyword to remove leading and trailing whitespaces
    }

    /**
     * Executes the find task command by searching for tasks containing the keyword
     * and displays them to the user. It leverages the task list to filter tasks based
     * on the keyword and uses the UI to display the found tasks.
     *
     * @param tasks   The task list containing the tasks to be searched.
     * @param ui      The UI component for displaying the found tasks.
     * @param storage The storage component, not used in this command.
     * @return A message indicating the result of the search operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> foundTasks = tasks.findTasks(keyword);
        return ui.showTaskList(foundTasks);
    }
}
