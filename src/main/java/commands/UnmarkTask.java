package commands;

import exception.InvalidCommandException;
import exception.InvalidIndexException;
import objects.Task;
import objects.TaskList;
import view.UnmarkedTask;

/**
 * The UnmarkTask class represents a command to unmark a task as not done in the TaskList.
 * It implements the Command interface and specifies the execution behavior for unmarking a task.
 */
public class UnmarkTask implements Command {

    /** The TaskList containing the task to be unmarked. */
    private final TaskList tasks;

    /** The index of the task to be unmarked. */
    private final int index;

    /**
     * Constructs an UnmarkTask command with the specified TaskList and index.
     *
     * @param tasks The TaskList containing the task to be unmarked.
     * @param index The index of the task to be unmarked.
     */
    public UnmarkTask(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Executes the UnmarkTask command by unmarking the task at the specified index as not done in the TaskList,
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

        tasks.unmarkTask(this.index);
        Task task = tasks.get(this.index);

        return UnmarkedTask.display(task);
    }
}
