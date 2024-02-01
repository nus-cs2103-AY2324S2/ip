package shodan.command;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.storage.StorageManager;
import shodan.ui.TermUi;

import java.util.List;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException;
}
