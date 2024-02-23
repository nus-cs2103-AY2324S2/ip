package jerry.command;

import jerry.Task;
import jerry.TaskList;
import jerry.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find and display tasks that contain a specific keyword.
 * <p>
 * This command filters tasks in the task list by checking if their descriptions contain the given keyword.
 * All matching tasks are then displayed to the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified UI, task list, and the keyword to search for.
     *
     * @param ui      The UI component for interacting with the user.
     * @param tasks   The task list to be searched.
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(Ui ui, TaskList tasks, String keyword) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.keyword = keyword;
    }

    /**
     * Executes the task search process.
     * <p>
     * Tasks whose descriptions contain the keyword are displayed to the user. If no tasks match,
     * a message indicating this is shown.
     */
    @Override
    public String execute() {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        return ui.showTaskSearchResults(foundTasks);
    }
}
