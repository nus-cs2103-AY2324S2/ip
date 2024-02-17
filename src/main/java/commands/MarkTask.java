package commands;

import exception.InvalidCommandException;
import exception.InvalidIndexException;
import objects.Task;
import objects.TaskList;

/**
 * The MarkTask class represents a command to mark a task as done in the TaskList.
 * It implements the Command interface and specifies the execution behavior for marking a task.
 */
public class MarkTask implements Command {

    /** The TaskList containing the task to be marked. */
    private final TaskList tasks;

    /** The index of the task to be marked. */
    private final int index;

    /**
     * Constructs a MarkTask command with the specified TaskList and index.
     *
     * @param tasks The TaskList containing the task to be marked.
     * @param index The index of the task to be marked.
     */
    public MarkTask(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Executes the MarkTask command by marking the task at the specified index as done in the TaskList,
     * and displaying a confirmation message.
     *
     * @return String
     * @throws InvalidIndexException   If the provided index is invalid (out of bounds).
     * @throws InvalidCommandException If the command is invalid (e.g., index is -1).
     */
    @Override
    public String execute() throws InvalidIndexException, InvalidCommandException {
        if (this.index == -1) {
            throw new InvalidCommandException();
        } else if (this.index < 0 || this.index > tasks.size() - 1) {
            throw new InvalidIndexException();
        }

        tasks.markTask(this.index);
        Task t = tasks.get(this.index);

        String output = String.format("Nice! I've marked this task as done:\n   %s", t.toString());

        return output;
    }
}
