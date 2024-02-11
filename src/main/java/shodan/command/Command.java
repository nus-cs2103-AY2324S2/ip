package shodan.command;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.storage.StorageManager;
import shodan.ui.TermUi;

/**
 * Represents a command entered by a user.
 */
public abstract class Command {
    /**
     * Execute the command using the resources that have been passed in.
     *
     * @param tasks          handle to the taskList
     * @param storageManager handle to the storageManager
     * @param ui             handle to the Ui object
     * @return True if Shodan should stop running after executing this command, False otherwise.
     * @throws ShodanException if there was an error when executing the command.
     */
    public abstract boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException;
}
