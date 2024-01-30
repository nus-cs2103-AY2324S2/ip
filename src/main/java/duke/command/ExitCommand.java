package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand implements Command {
    /**
     * Displays exit message.
     *
     * @param list Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @throws DukeException No thrown.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
