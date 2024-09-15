package cat.command;

import cat.Storage;
import cat.TaskList;
import cat.ui.Ui;
import cat.ui.response.Response;

/**
 * A command to list all the tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public Response execute(TaskList tasks) {
        assert tasks != null : "The task list must not be null";
        if (tasks.isEmpty()) {
            return Ui.showNote("You have no tasks added");
        }
        return Ui.showNote("Your tasks:\n" + tasks);
    }
}
