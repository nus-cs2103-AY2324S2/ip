package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * A command to find the tasks matching a certain query.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Construct a task that filters the list of tasks that contain the string query.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(query);
        ui.showNote("Found tasks:\n" + matchingTasks);
    }
}
