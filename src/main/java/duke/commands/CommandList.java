package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents the command for listing all tasks in the task list in the Duke application.
 */
public class CommandList extends Command {
    /**
     * Constructs a new CommandList object.
     */
    public CommandList() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] outputs = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputs[i] = String.format("%d. %s", i + 1, task);
        }

        return String.join("\n", outputs);
    }
}
