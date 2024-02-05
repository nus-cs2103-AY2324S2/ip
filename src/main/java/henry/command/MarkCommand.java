package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand object.
     * @param args The arguments of the command.
     * @throws HenryException If the command is invalid.
     */
    public MarkCommand(String args) throws HenryException {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        tasks.markTask(index);
        storage.save(tasks);
    }
}
