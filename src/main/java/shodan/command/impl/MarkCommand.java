package shodan.command.impl;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.tasks.Task;
import shodan.ui.TermUi;

import java.util.List;

public class MarkCommand extends Command {
    boolean toMark;
    int index;
    public MarkCommand(int index, boolean toMark) {
        this.index = index;
        this.toMark = toMark;
    }
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException {
        try {
            Task task = tasks.mark(index, toMark);
            storageManager.saveTasks(tasks.getTasks());
            if (toMark) {
                ui.printMsg("Task set to done:\n\t" + task);
            } else {
                ui.printMsg("Task has been set as not done yet:\n\t" + task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ShodanException("Couldn't find task with that number. Use the list command to view all current tasks.");
        }
        return false;
    }
}
