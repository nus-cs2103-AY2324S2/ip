package duke.command;

import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * ListCommand class
 */
public class TaskListingCommand extends Command {
    @Override
    public String getExecutionMessage(TaskList tasks, Ui ui, Storage storage) {
        return tasks.getListTasksMessage();
    }
}
