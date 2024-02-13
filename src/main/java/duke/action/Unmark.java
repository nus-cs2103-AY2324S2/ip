package duke.action;

import duke.task.Task;

/**
 * Represents an action to unmark a task as done.
 */

public class Unmark implements Action {
    private Task unmarkedTask;

    public Unmark(Task unmarkedTask) {
        this.unmarkedTask = unmarkedTask;
    }

    /**
     * Gets the response message indicating the successful unmarking of a task as not done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        return " OK, I've marked this task as not done yet:\n" + unmarkedTask.toString();
    }
}
