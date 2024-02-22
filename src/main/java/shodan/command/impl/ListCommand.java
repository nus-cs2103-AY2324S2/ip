package shodan.command.impl;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.ui.TermUi;

/**
 * List the current tasks.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException {
        ui.listTasks(tasks.getTasks());
        return false;
    }
}
