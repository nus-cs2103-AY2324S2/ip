package duke.action;

/**
 * Represents an action to mark a task as done.
 */

public class Mark implements Action {

    /**
     * Gets the response message indicating the successful marking of a task as done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        return " Nice! I've marked this task as done:";
    }
}


