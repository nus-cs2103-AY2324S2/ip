package maltese.action;

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
    public String getResponse() {
        System.exit(0);
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Parses the farewell command and creates a Farewell action.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A Farewell action.
     */

    public static Farewell parse(TaskList taskList) {
        return new Farewell();
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


