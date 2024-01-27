package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command to list all the tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showNote("Your tasks:\n" + tasks.toString() );
    }
}
