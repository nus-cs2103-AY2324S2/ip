package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int index;
    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * Initializes an UnmarkCommand with the provided task index. If the index is blank,
     * throws a SamException with a message indicating the need to provide a task index.
     *
     * @param index the index of the task to unmark as undone
     * @throws SamException if the provided index is blank
     */
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
