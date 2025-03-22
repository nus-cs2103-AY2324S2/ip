package exception;

/**
 * InvalidIndexException is a specific type of DukeException that represents an error
 * when an invalid index is encountered in the Duke application, typically related to task manipulation.
 * It provides a predefined error message indicating that the index is out of bounds.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructs an InvalidIndexException with a predefined error message indicating
     * that the index is out of bounds.
     */
    public InvalidIndexException() {
        super("Index is out of bounds");
    }
}
