package numerator.exceptions.parser;

/**
 * Signals that the task index is out of bounds
 */
public class TaskIndexOutOfBoundsException extends ParserException {
    /**
     * Constructs a TaskIndexOutOfBoundsException with the specified detail message
     *
     * @param message should contain information about the exception
     */
    public TaskIndexOutOfBoundsException(String message) {
        super(message);
    }
}
