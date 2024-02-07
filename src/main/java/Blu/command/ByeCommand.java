package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to terminate the application.
 * When executed, this command displaying an exit message in the UI.
 */
public class ByeCommand extends Command {

    /**
     * Displays exit message in the UI.
     *
     * @param taskList The current TaskList (not applicable for ByeCommand).
     * @param storage The Storage (not applicable for ByeCommand).
     * @param ui The UI responsible for user interactions.
     * @return The exit message to be displayed to the user.
     * @throws BluException If an error occurs during execution (not applicable for ByeCommand).
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        return ui.showExitMessage();
    }
}
