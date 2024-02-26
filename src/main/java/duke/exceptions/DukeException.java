package duke.exceptions;

/**
 * The DukeException class represents a custom exception for Duke.
 * <p>
 * This exception is used to indicate errors that occur within the Duke application.
 * </p>
 * <p>
 * It extends the Exception class.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see java.lang.Exception
 */
public class DukeException extends Exception{

    /**
     * Constructs a new DukeException with the specified detail message.
     *
     * @param message the detail message.
     */
    public DukeException(String message) {
        super("Error: " + message);
    }

}
