package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Shows all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.showList(taskList);
    }
}
