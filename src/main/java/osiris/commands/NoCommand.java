package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing a no-operation command.
 * This command does nothing when executed.
 * Called when an exception is thrown.
 */
public class NoCommand extends Command {

    /**
     * Executes the no-operation command.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return String notification if the command is executed successfully.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        return "";
    }
}
