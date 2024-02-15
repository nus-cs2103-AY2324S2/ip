package exception;

/**
 * Represents an exception when a command is incomplete.
 */
public class IncompleteCommandException extends Exception {
    private String taskType;

    /**
     * Constructor for IncompleteCommandException.
     * @param taskType Type of task that is incomplete.
     */
    public IncompleteCommandException(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns the error message when a command is incomplete.
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a " + this.taskType + " command cannot be empty.";
    }
}
