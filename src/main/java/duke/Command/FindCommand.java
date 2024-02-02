package duke.Command;

import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;
import duke.Tasks.Task;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a command to find tasks containing a specified keyword.
 */

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks containing the keyword.
     * Displays the matching tasks in the UI.
     *
     * @param tasks   The list of tasks to search within.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task.getTask().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return False, as FindCommand does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

