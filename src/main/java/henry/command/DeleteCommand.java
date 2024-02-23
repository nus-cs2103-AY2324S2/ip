package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand object.
     *
     * @param args The arguments of the command.
     * @throws HenryException If the command is invalid.
     */
    public DeleteCommand(String args) throws HenryException {
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
        String deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        return deletedTask;
    }
}
