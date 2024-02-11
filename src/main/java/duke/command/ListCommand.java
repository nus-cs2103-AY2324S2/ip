package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * List all tasks in our task list.
 */
public class ListCommand extends Command {
    /**
     * List all tasks.
     *
     * @param tasks   The list of tasks.
     * @param storage Storage interface for persistence.
     * @return The list of tasks (as a string).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        return "Here are the tasks in your list:\n" + tasks + "\n";
    }

    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
