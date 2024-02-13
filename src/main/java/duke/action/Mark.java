package duke.action;

import duke.task.Task;

/**
 * Represents an action to delete a task from the task list.
 */
public class Mark implements Action {

    /**
     * The task that has been deleted.
     */
    private Task markedTask;


    /**
     * Constructs a Mark action with the specified task to be marked
     *
     * @param markedTask The task to be deleted.
     */
    public Mark(Task markedTask) {
        this.markedTask = markedTask;
    }

    /**
     * Gets the response message indicating the marking of the task.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        return "Nice! I've marked this task as done\n" + markedTask.toString();
    }
}

