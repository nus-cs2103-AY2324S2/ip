package duke.action;

/**
 * An interface representing an action in the Duke application.
 */
public interface Action {

    /**
     * Gets the response associated with the action.
     *
     * @return A string representing the response of the action.
     */
    String response();

    /**
     * Checks if the action represents an exit command.
     *
     * @return {@code true} if the action is an exit command, {@code false} otherwise.
     */
    default boolean isExit() {
        return false;
    }
}
