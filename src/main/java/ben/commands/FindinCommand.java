package ben.commands;

import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword of a specified type in the task description.
 */
public class FindinCommand extends Command {
    private final String keyword;
    private final String taskType;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindinCommand(String keyword, String taskType) {
        this.keyword = keyword;
        this.taskType = taskType;
    }

    /**
     * Executes the find command, searching for tasks containing the specified keyword.
     *
     * @param tasks   The TaskList containing tasks to search.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // obtain list of matched tasks
        TaskList matchedTasks = tasks.findinTasks(keyword, taskType);

        if (matchedTasks.isEmpty()) {
            return ui.showNoTasksFound();
        } else {
            return ui.showTasksFound(matchedTasks);
        }
    }
}
