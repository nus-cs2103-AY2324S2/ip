package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

/**
 * Command to list all tasks in the list.
 */
public class ListCommand extends Command{

    /**
     * {inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }
}
