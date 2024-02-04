package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

/**
 * Represents the command to list all tasks in the taskList.
 */
public class ListCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.displayAllTasks(taskList);
    }
}
