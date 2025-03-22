package commands;

import exception.InvalidIndexException;
import objects.Task;
import objects.TaskList;
import view.DeletedAllTasks;
import view.DeletedTask;

/**
 * The DeleteTask class represents a command to delete a task from the TaskList.
 * It implements the Command interface and specifies the execution behavior for deleting a task.
 */
public class DeleteTask implements Command {

    /** The TaskList from which the task will be deleted. */
    private TaskList tasks;

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
        if (this.index < -1 || this.index >= tasks.size()) {
            throw new InvalidIndexException();
        }

        if (index == -1) {
            tasks.clear();

            return DeletedAllTasks.display();
        } else {
            Task t = tasks.get(this.index);
            tasks.remove(this.index);

            return DeletedTask.display(this.tasks, t);
        }
    }
}
