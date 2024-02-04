package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents the command to list all tasks in the taskList.
 */
public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.displayAllTasks(taskList);
    }
}
