package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command to find the tasks matching a certain query.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Construct a task that filters the list of tasks that contain the string query.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showNote("Found tasks:\n" + tasks.find(query));
    }
}
