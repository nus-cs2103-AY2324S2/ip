package duke.exceptions;

/**
 * The InvalidIndexException class represents an exception indicating an invalid index.
 * <p>
 * This exception is thrown when an index provided by the user is out of bounds.
 * </p>
 * <p>
 * It extends the DukeException class.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.exceptions.DukeException
 */
public class InvalidIndexException extends DukeException{

    /**
     * Constructs a new InvalidIndexException with a default message.
     */
    public InvalidIndexException() {
        super("Index out of bounds. Use list to view all duke.tasks.");
    }
}
