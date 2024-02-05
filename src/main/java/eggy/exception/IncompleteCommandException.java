package eggy.exception;

/**
 * Represents an exception when the user does not specify the task number of the task to be modified.
 */
public class IncompleteCommandException extends EggyException {
    /**
     * Constructs the IncompleteCommandException.
     *
     * @param commandType The type of command that the user did not specify the task number for.
     */
    public IncompleteCommandException(String commandType) {
        super(" The task number to be " + commandType + (commandType.equals("delete") ? "d" : "ed")
                + " is not specified.");
    }
}
