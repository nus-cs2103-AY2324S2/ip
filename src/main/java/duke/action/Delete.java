package duke.action;

import duke.task.Task;

/**
 * Represents an action to delete a task from the task list.
 */
public class Delete implements Action {
<<<<<<< HEAD

    /**
     * The task that has been deleted.
     */
    public Task deletedTask;
=======
    private Task deletedTask;
>>>>>>> A-CodingStandard

    /**
     * Constructs a Delete action with the specified task to be deleted.
     *
     * @param deletedTask The task to be deleted.
     */
    public Delete(Task deletedTask) {
        this.deletedTask = deletedTask;
    }

    /**
     * Gets the response message indicating the deletion of the task.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
<<<<<<< HEAD
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\nNow you have " +
                " tasks in the list.";
=======
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\nNow you have "
            + " tasks in the list.";
>>>>>>> A-CodingStandard
    }
}


