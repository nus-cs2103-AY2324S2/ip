package duke.exceptions;

/**
 * The StorageException class represents an exception indicating an error in loading or saving a file.
 * <p>
 * This exception is thrown when there is an error in loading or saving a file during storage operations.
 * </p>
 * <p>
 * It extends the DukeException class.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.exceptions.DukeException
 */
public class StorageException extends DukeException {

    /**
     * Constructs a new StorageException with a default message.
     */
    public StorageException() {
        super("Error loading/saving file.");
    }

}
