package shon.command;

import shon.TaskList;

/**
 * Represents a command to list the tasks in the <code>TaskList</code>.
 */
public class ListCommand extends Command {
    /**
     * List the tasks in the tasks <code>TaskList</code> in a user-friendly format.
     *
     * @param tasks The <code>TaskList</code> containing the tasks of the user.
     */
    @Override
    public String execute(TaskList tasks) {
        return String.join("\n", tasks.getTasks());
    }
}
