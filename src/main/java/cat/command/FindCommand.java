package cat.command;

import cat.Storage;
import cat.TaskList;
import cat.ui.Ui;
import cat.ui.response.Response;

/**
 * A command to find the tasks matching a certain query.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Constructs a task that filters the list of tasks that contain the string query.
     */
    public FindCommand(String query) {
        assert query != null : "Query must not be null";
        this.query = query;
    }

    @Override
    public Response execute(TaskList tasks, Storage storage) {
        TaskList matchingTasks = tasks.find(query);
        return Ui.showNote("Found tasks:\n" + matchingTasks);
    }
}
