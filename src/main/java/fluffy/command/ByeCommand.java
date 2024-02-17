package fluffy.command;

import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to exit the program.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns whether the command is an exit command.
     * @return Whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
