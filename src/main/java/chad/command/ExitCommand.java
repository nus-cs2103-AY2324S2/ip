package chad.command;

import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Exits the application.
     * @param list the task list
     * @param ui Chad UI
     * @param s Chad Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.farewell();
    }

    public boolean isExit() {
        return true;
    };
}
