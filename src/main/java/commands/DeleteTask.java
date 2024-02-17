package commands;

import exception.InvalidIndexException;
import objects.Task;
import objects.TaskList;

/**
 * The DeleteTask class represents a command to delete a task from the TaskList.
 * It implements the Command interface and specifies the execution behavior for deleting a task.
 */
public class DeleteTask implements Command {

    /** The TaskList from which the task will be deleted. */
    private final TaskList tasks;

    /** The index of the task to be deleted. */
    private final int index;

    /**
     * Constructs a DeleteTask command with the specified TaskList and index.
     *
     * @param tasks The TaskList from which the task will be deleted.
     * @param index The index of the task to be deleted.
     */
    public DeleteTask(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    /**
     * Executes the DeleteTask command by removing the task at the specified index from the TaskList,
     * and displaying a confirmation message.
     *
     * @return String
     * @throws InvalidIndexException If the provided index is invalid (out of bounds).
     */
    @Override
    public String execute() throws InvalidIndexException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new InvalidIndexException();
        }

        Task t = tasks.get(this.index);

        tasks.remove(this.index);
        String output = String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.",
                t.toString(), tasks.size());

        return output;
    }
}
