package arc.commands;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.Task;
import arc.tasks.TaskList;

/**
 * Represents the command for listing all tasks in the task list in the Arc application.
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
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        String[] outputs = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputs[i] = String.format("%d. %s", i + 1, task);
        }

        return String.join("\n", outputs);
    }
}
