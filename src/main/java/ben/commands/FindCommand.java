package ben.commands;

import ben.storage.Storage;
import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.ui.Ui;

import java.util.List;

/**
 * Represents a command to find tasks containing a specific keyword in the task description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
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
        List<Task> matchedTasks = tasks.findTasks(keyword);

        if (matchedTasks.isEmpty()) {
            return ui.showNoTasksFoundMessage();
        } else {
            StringBuilder output = new StringBuilder();
            output.append(ui.showTasksFoundMessage());
            for (int i = 0; i < matchedTasks.size(); i++) {
                Task currTask = matchedTasks.get(i);
                output.append(Ui.show((i + 1) + ". " + currTask));
            }

            return output.toString();
        }
    }
}
