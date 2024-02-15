package duke.action;

import duke.exception.WrongIndexException;
import duke.task.Task;

/**
 * Represents an action to mark a task as done in the task list.
 */
public class Mark implements Action {

    /**
     * The index of the task to be marked as done.
     */
    private int index;

    /**
     * The task list containing the tasks.
     */
    private TaskList tasks;

    /**
     * Constructs a Mark action with the specified index and task list.
     *
     * @param index The index of the task to be marked as done.
     * @param tasks The task list containing the tasks.
     * @throws WrongIndexException If the index is invalid.
     */
    public Mark(int index, TaskList tasks) throws WrongIndexException {
        if (!(tasks.validateIndex(index))) {
            throw new WrongIndexException();
        }
        this.index = index;
        tasks.markTask(index);
        this.tasks = tasks;
    }

    /**
     * Gets the response message indicating the marking of the task as done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        Task markedTask = tasks.get(index);
        return "Nice! I've marked this task as done\n" + markedTask.toString();
    }
}

