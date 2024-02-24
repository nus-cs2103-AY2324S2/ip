package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String index) throws SamException {
        if (index.isBlank()) {
            throw new SamException("Please provide a task number.");
        }
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new SamException("Please provide a number");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        tasks.unmarkTask(index);
        storage.save(tasks);
    }
}