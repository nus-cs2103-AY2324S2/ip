package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command show list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the command, displaying the list to user.
     *
     * @param list TaskList object containing current tasks.
     * @param ui Not used
     * @param storage Not used
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showList(list);
    }
}
