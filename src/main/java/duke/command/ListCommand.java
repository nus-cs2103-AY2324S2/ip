package duke.command;

import duke.TaskList;

/**
 * List all tasks in our task list.
 */
public class ListCommand extends Command {
    /**
     * List all tasks.
     *
     * @param tasks   The list of tasks.
     * @return The list of tasks (as a string).
     */
    @Override
    public String execute(TaskList tasks) {
        assert tasks != null;
        return "Here are the tasks in your list:\n" + tasks + "\n";
    }

    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
