package duke.command;

import duke.utility.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Exits the application.
     * @param list the task list
     * @param ui Duke UI
     * @param s Duke Storage
     */
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.farewell();
    }

    public boolean isExit() {
        return true;
    };
}
