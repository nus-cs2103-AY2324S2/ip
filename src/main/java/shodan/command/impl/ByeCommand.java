package shodan.command.impl;

import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.ui.TermUi;

/**
 * Created when the user tells the chatbot to stop running.
 * Prints an exit message, and signals to the main loop to exit.
 */
public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) {
        ui.showExitMsg();
        return true;
    }
}
