package shodan.command.impl;

import java.util.List;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.tasks.Task;
import shodan.tasks.TaskParser;
import shodan.tasks.TaskType;
import shodan.ui.TermUi;

/**
 * Adds a new task to the current list.
 */
public class AddCommand extends Command {
    private List<String> args;
    private TaskType type;

    /**
     * Instantiates a new Add command.
     *
     * @param args     the list of arguments passed in
     * @param taskType the type of the task to add.
     */
    public AddCommand(List<String> args, TaskType taskType) {
        this.args = args;
        type = taskType;
    }

    /**
     * {@inheritDoc}
     */
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException {
        Task task = TaskParser.parse(args, type);
        int size = tasks.add(task);
        storageManager.saveTasks(tasks.getTasks());
        ui.printMsg("Task has been added:\n\t" + task);
        ui.printMsg(String.format("You have %d tasks now.\n", size));
        return false;
    }
}
