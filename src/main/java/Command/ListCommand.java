package Command;

import Utility.TaskList;
import Utility.Ui;
import Utility.Storage;

/**
 * Represents a command to show the current {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Shows the current task list.
     *
     * @param list the task list
     * @param ui Duke UI
     * @param s Duke Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.listTasks(list.getList());
    }

    public boolean isExit() {
        return false;
    }
}
