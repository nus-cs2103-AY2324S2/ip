package duke.action;

import duke.exception.WrongIndexException;
import duke.task.Task;

/**
 * Represents an action to delete a task.
 */
public class Delete implements Action {
    private int index;
    private Task deletedTask;
    private TaskList tasks;

    /**
     * Constructs a Delete action with the specified index and task list.
     *
     * @param index The index of the task to be deleted.
     * @param tasks The task list from which the task will be deleted.
     * @throws WrongIndexException If the index is invalid.
     */

    public Delete(int index, TaskList tasks) throws WrongIndexException {
        if (!tasks.validateIndex(index)) {
            throw new WrongIndexException();
        }
        this.index = index;
        this.deletedTask = tasks.get(index); // Retrieve the task before deletion
        assert deletedTask != null : "Deleted task cannot be null";
        tasks.deleteTask(index);
        this.tasks = tasks;
    }

    /**
     * Gets the response message indicating the successful deletion of a task.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\nNow you have "
                + tasks.size() + " tasks in the list.";
    }
}

