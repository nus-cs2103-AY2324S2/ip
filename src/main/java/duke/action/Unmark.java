package duke.action;

/**
 * Represents an action to unmark a task as done.
 */

public class Unmark implements Action {

    /**
     * Gets the response message indicating the successful unmarking of a task as not done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        return " OK, I've marked this task as not done yet:";
    }
}


