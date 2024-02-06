package duke.action;

/**
 * Represents an action to bid farewell to the user.
 */

public class Farewell implements Action {

    /**
     * Gets the farewell message.
     *
     * @return A string representing the farewell message.
     */
    @Override
    public String response() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Checks if the action represents an exit command.
     *
     * @return {@code true} as the farewell action signifies an exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

