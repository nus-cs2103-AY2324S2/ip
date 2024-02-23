package eggy.exception;

/**
 * Represents an exception when the task number is not an integer.
 */
public class TaskNumberFormatException extends EggyException {
    /**
     * Constructs a TaskNumberFormatException.
     */
    public TaskNumberFormatException() {
        super(" Task number must be an integer.");
    }
}
