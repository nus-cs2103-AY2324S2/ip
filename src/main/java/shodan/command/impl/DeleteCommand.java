package shodan.command.impl;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.tasks.Task;
import shodan.ui.TermUi;

import java.util.List;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException {
        try {
            Task task = tasks.delete(index);
            storageManager.saveTasks(tasks.getTasks());
            ui.printMsg("The following task has been removed:\n\t" + task);
            ui.printMsg(String.format("There are now %d tasks remaining in the list.\n", tasks.getTasks().size()));
        } catch (IndexOutOfBoundsException e) {
            throw new ShodanException("Couldn't find task with that number. "
                    + "Use the list command to view all current tasks.");
        }
        return false;
    }
}
