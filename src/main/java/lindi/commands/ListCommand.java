package lindi.commands;

import lindi.storage.Storage;
import lindi.task.TaskList;

/**
 * Represents a command that lists out tasks in the task list upon execution
 */
public class ListCommand extends Command {
    public static final String COMMAND_LIST = "list";
    /**
     * {@inheritDoc}
     * <p>
     * Loads the tasks in task list into status msg to be printed.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        this.statusMsg = tasks.toString();
    }
}
