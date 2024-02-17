package numerator.exceptions.parser;

/**
 * Signals that the task index is out of bounds.
 */
public class TaskIndexOutOfBoundsException extends ParserException {
    /**
     * Constructs a TaskIndexOutOfBoundsException with the specified detail message.
     */
    public TaskIndexOutOfBoundsException() {
        super("Task does not exist at the specified index!");
    }
}
