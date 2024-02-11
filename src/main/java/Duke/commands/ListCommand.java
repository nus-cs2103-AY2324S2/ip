package duke.commands;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute ListCommand
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    /**
     * Executes the find command, searching for tasks containing the specified keyword.
     *
     * @param tasks The TaskList containing the list of tasks.
     * @param ui The UI object for displaying messages.
     * @param storage The Storage object for saving data to a file.
     * @return False indicating that the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) {
        ui.displayList(tasks.getItems());
        return false;
    }
}
