package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;
    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * Initializes a MarkCommand with the provided task index. If the index is blank,
     * throws a SamException with a message indicating the need to provide a task index.
     *
     * @param index the index of the task to mark as done
     * @throws SamException if the provided index is blank
     */
    public MarkCommand(String index) throws SamException {
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
    public String execute(TaskList tasks, Storage storage) throws SamException {
        Task task = tasks.markTask(index);
        storage.save(tasks);
        return String.format("This task has been marked as done:\n%s\n", task);
    }
}
