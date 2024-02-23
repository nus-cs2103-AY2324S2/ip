package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.task.Task;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates a UnmarkCommand object.
     *
     * @param args The arguments of the command.
     * @throws HenryException If the command is invalid.
     */
    public UnmarkCommand(String args) throws HenryException {
        if (args.isBlank()) {
            throw new HenryException("No index provided");
        }
        try {
            this.index = Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new HenryException("Index provided is not a number");
        }
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws HenryException {
        Task task = tasks.unmarkTask(index);
        storage.save(tasks);
        return String.format("This task has been marked as undone. :(\n%s\n", task);
    }
}
