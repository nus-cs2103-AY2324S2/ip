package cat.command;

import cat.Storage;
import cat.TaskList;
import cat.ui.Ui;

/**
 * A command to list all the tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "The task list must not be null";
        assert ui != null : "The ui must not be null";

        ui.showNote("Your tasks:\n" + tasks.toString());
    }
}
