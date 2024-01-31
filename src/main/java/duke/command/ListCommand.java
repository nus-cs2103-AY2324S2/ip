package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * List all tasks in our task list.
 */
public class ListCommand extends Command {
    /**
     * List all tasks.
     *
     * @param tasks   The list of tasks.
     * @param ui      UI interface with the user.
     * @param storage Storage interface for persistence.
     * @return The list of tasks (as a string).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Here are the tasks in your list:\n" + tasks + "\n";
    }

    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
