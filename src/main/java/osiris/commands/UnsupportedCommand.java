package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing an unsupported command.
 */
public class UnsupportedCommand extends Command {

    /**
     * Executes the unsupported command by displaying a message through the user interface.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return String notification of unsupported command being entered.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        return userInterface.displayUnsupportedCommandMessage();
    }
}
