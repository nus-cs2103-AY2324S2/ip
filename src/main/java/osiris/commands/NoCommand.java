package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing a no-operation command.
 * This command does nothing when executed.
 */
public class NoCommand extends Command {

    /**
     * Executes the no-operation command.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return Always returns true.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        return true;
    }
}
