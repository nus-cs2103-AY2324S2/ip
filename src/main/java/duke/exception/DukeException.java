package duke.exception;

/**
 * A custom runtime exception class for Duke-specific errors.
 *
 * <p>The {@code DukeException} class extends {@code RuntimeException} and is used to represent
 * runtime errors specific to the Duke chatbot application. It provides a way to handle and propagate
 * exceptions related to Duke's functionality.</p>
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}
