package felix.command;

import felix.exception.FelixException;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing the command to stop the application
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Stops execution of program.
     * Returns String representation of display message.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        return ui.getExitMessage();
    }
}
