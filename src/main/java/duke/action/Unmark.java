package duke.action;

import duke.exception.WrongIndexException;
import duke.task.Task;

/**
 * Represents an action to unmark a task as done.
 */

public class Unmark implements Action {
    private int index;
    private TaskList tasks;

    public Unmark(int index, TaskList tasks) throws WrongIndexException {
        if (!(tasks.validateIndex(index))) {
            throw new WrongIndexException();
        }
        this.index = index;
        tasks.unmarkTask(index);
        this.tasks = tasks;
    }

    /**
     * Gets the response message indicating the successful unmarking of a task as not done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        Task unmarkedTask = tasks.get(index);
        return " OK, I've marked this task as not done yet:\n" + unmarkedTask.toString();
    }
}
